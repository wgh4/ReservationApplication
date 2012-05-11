package ie.cit.clouddevproject;

import java.util.Date;

public class Reservation {
	private int id;
	private String comments;
	private String trainer;
	private String pitch;
	private Date submitdate;
	private String submittime;
	private Date reservationdate;
	private String reservationtime;
	private String team;

	public String getComments() {
		return comments;
	}

	public void setComments(String strComments) {
		this.comments = strComments;
	}
	
	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String strTrainer) {
		this.trainer = strTrainer;
	}

	public String getPitch() {
		return pitch;
	}

	public void setPitch(String strPitch) {
		this.pitch = strPitch;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String strTeam) {
		this.team = strTeam;
	}

	public Date getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(Date dteSubmitDate) {
		this.submitdate = dteSubmitDate;
	}

	public String getSubmittime() {
		return submittime;
	}

	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}

	public Date getReservationdate() {
		return reservationdate;
	}

	public void setReservationdate(Date dteReservationDate) {
		this.reservationdate = dteReservationDate;
	}

	public String getReservationtime() {
		return reservationtime;
	}

	public void setReservationtime(String reservationtime) {
		this.reservationtime = reservationtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
