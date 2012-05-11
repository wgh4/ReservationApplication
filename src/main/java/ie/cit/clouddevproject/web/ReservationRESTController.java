package ie.cit.clouddevproject.web;

import ie.cit.clouddevproject.JdbcReservationDatabase;
import ie.cit.clouddevproject.Reservation;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
public class ReservationRESTController {

	@Autowired
	private JdbcReservationDatabase dbResDBS;

	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public @ResponseBody
	List<Reservation> listReservations() {
		return dbResDBS.getAll();
	}

	@RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.GET)
	public @ResponseBody
	Reservation objReservation(@PathVariable Integer iReservationID) {
		return dbResDBS.get(iReservationID);
	}

	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createReservation(@RequestBody Reservation objReservation, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		dbResDBS.save(objReservation);
		response.addHeader("Location",
				getLocationForChildResource(request, objReservation.getId()));
	}

	private String getLocationForChildResource(HttpServletRequest request,
			Integer childIdentifier) {
		// get the current URL from the request
		final StringBuffer url = request.getRequestURL();
		// append the /xyz to the URL and make it a UriTemplate
		final UriTemplate template = new UriTemplate(url.append("/{childId}")
				.toString());
		return template.expand(childIdentifier).toASCIIString();
	}

	@RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateRegistration(@PathVariable Integer iReservationID, @RequestBody Reservation objReservation) {
		Reservation current = dbResDBS.get(iReservationID);
		current.setComments(objReservation.getComments());
		current.setPitch(objReservation.getPitch());
		current.setReservationdate(objReservation.getReservationdate());
		current.setTeam(objReservation.getTeam());
		dbResDBS.update(current);
	}

	@RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTodo(@PathVariable Integer iReservationID) {
		dbResDBS.delete(iReservationID);
	}
}

//get   : curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" http://localhost:8080/reservationapp/rest/reservation
//create: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X POST -d '{"comment": "buy milk"}' http://localhost:8080/reservationapp/rest/reservation
//get   : curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" http://localhost:8080/reservationapp/rest/reservation/1
//update: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X PUT -d '{"comment": "buy milk", "done": true}' http://localhost:8080/reservationapp/rest/reservation/1
//delete: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X DELETE http://localhost:8080/reservationapp/rest/reservation/1
