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

//GET ALL TESTED AND WORKING
//SYNTAX: curl -i --user traineru7:wghu72012 http://localhost:8080/reservationapp/rest/reservation

	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public @ResponseBody
	List<Reservation> listReservations() {
		return dbResDBS.getAll();
	}

//GET SINGLE RESERVATION TESTED AND WORKING
//SYNTAX: curl -i --user traineru7:wghu72012 http://localhost:8080/reservationapp/rest/reservation/1
		
	@RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.GET)
	public @ResponseBody
	Reservation objReservation(@PathVariable Integer reservationId) {
		return dbResDBS.get(reservationId);
	}

//CREATE SINGLE RESERVATION TESTED AND WORKING
// create: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X POST -d '{"comments":"Weekly Training","pitch":"Main pitch","team":"u7","reservationdate":"05/12/2012 10:00"}' http://localhost:8080/reservationapp/rest/reservation
	
	@RequestMapping(value = "reservation", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createReservation(@RequestBody Reservation objReservation, HttpServletRequest httpRequest,
			HttpServletResponse response) throws ParseException { 
		dbResDBS.save(objReservation);
		String url = getLocationReservation(httpRequest, objReservation.getId());
		response.addHeader("Location", url );
	}

//As per Igor's example	create getLocationForReservation
	private String getLocationReservation(HttpServletRequest httpRequest, int reservationID) {

		// get the current URL from the request
		 StringBuffer requestURL = httpRequest.getRequestURL();

		// append the /{reservationid} to the URL and make it a UriTemplate
		UriTemplate uriTemplate = new UriTemplate(requestURL
				.append("/{reservationID}").toString());
		return uriTemplate.expand(reservationID).toASCIIString();
	}
	
//DELETION TESTED and WORKING
//delete: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X DELETE http://localhost:8080/reservationapp/rest/reservation/1

	@RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTodo(@PathVariable Integer reservationId) {
		dbResDBS.delete(reservationId);
	}
}

