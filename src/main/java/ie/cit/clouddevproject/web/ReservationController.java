package ie.cit.clouddevproject.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import ie.cit.clouddevproject.JdbcReservationDatabase;
import ie.cit.clouddevproject.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("reservations")
@Controller
public class ReservationController {

	@Autowired
	private JdbcReservationDatabase dbResDBS;

	@RequestMapping(method = RequestMethod.GET)
	public void listReservations(Model model) {
		model.addAttribute("reservations", dbResDBS.getAll());
		model.addAttribute("myreservations", dbResDBS.getUserReservations());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void createReservation(Model model, @RequestParam String comments, @RequestParam String pitch, @RequestParam String team, @RequestParam String reservationdate) throws ParseException {
		Reservation objReservation = new Reservation();
		objReservation.setComments(comments);
		objReservation.setPitch(pitch);
		objReservation.setTeam(team);
		//Convert string from form to date object
		System.out.print( reservationdate );
		SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy HH:mm");
		objReservation.setReservationdate(formatter.parse(reservationdate));

		dbResDBS.save(objReservation);
		model.addAttribute("reservations", dbResDBS.getAll());
		model.addAttribute("myreservations", dbResDBS.getUserReservations());

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteReservation(Model model, @RequestParam int iReservationID) {
		dbResDBS.delete(iReservationID);
		model.addAttribute("reservations", dbResDBS.getAll());
		model.addAttribute("myreservations", dbResDBS.getUserReservations());

	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateReservation(Model model, @RequestParam String comments, @RequestParam int iReservationID, @RequestParam String pitch, @RequestParam String team, @RequestParam String reservationdate) throws ParseException {
		Reservation objReservation = dbResDBS.get(iReservationID);
		objReservation.setPitch(objReservation.getPitch());
		objReservation.setComments(comments);
		objReservation.setPitch(pitch);
		
		//Convert string from form to date object
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm");
		objReservation.setReservationdate(sdf.parse(reservationdate));

		dbResDBS.update(objReservation);
		model.addAttribute("reservations", dbResDBS.getAll());
		model.addAttribute("myreservations", dbResDBS.getUserReservations());

	}

}
