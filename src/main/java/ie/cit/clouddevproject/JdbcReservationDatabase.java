package ie.cit.clouddevproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

@Secured("ROLE_MEMBER")
public class JdbcReservationDatabase {

	private JdbcTemplate jdbcTemplate;

	public JdbcReservationDatabase(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	JdbcReservationDatabase() {
	}

	public void save(Reservation objReservation) throws DataAccessException {
		
		jdbcTemplate.update(
				"insert into PITCH_RESERVATIONS (comments, trainer, team, pitch, submit_date, reservation_date) values(?,?,?,?,?,?)",
				objReservation.getComments(),  getCurrentUser(), objReservation.getTeam(),objReservation.getPitch(),getCurrentDate(), objReservation.getReservationdate());
		int id = jdbcTemplate.queryForInt( "select max(id) from PITCH_RESERVATIONS" );
		objReservation.setId(id);
	}

	public Reservation get(int iID) {
		return jdbcTemplate.queryForObject(
				"select id, comments, trainer, team,  pitch, submit_date, reservation_date from PITCH_RESERVATIONS where id=? and owner=?",
				new ReservationMapper(), iID, getCurrentUser());
	}

//Get a List of all the reservations of all users that are today or in the future	
	public List<Reservation> getAll() {
		return jdbcTemplate.query(
				"select id, comments, trainer, team, pitch, submit_date, reservation_date from PITCH_RESERVATIONS where reservation_date >= curdate()",
				new ReservationMapper());
	}

//Get a List of all the reservations of a specific users that are today or in the future
	public List<Reservation> getUserReservations() {
		return jdbcTemplate.query(
				"select id, comments, trainer, team, pitch, submit_date, reservation_date from PITCH_RESERVATIONS where reservation_date >= curdate() and trainer=?",
				new ReservationMapper(), getCurrentUser());
	}

//Get the name of the Authenticated User	
	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

//Get the Current Date & Time
	private Date getCurrentDate() {
		  java.util.Date date = new java.util.Date();
		  return date;
	}
	
//A trainer can only delete his/her own reservations
	public void delete(int iID) {
		jdbcTemplate.update("delete from PITCH_RESERVATIONS where id=? and trainer=?", iID,
				getCurrentUser());
	}

	public void update(Reservation objReservation) {
		jdbcTemplate.update(
				"update PITCH_RESERVATIONS set comment=?, reservationdate=? , pitch= ?, team =? where id=? and trainer=?",
				objReservation.getComments(), objReservation.getReservationdate(), objReservation.getPitch(), objReservation.getTeam(),objReservation.getId(), getCurrentUser());
	}
}

class ReservationMapper implements RowMapper<Reservation> {

	public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reservation objReservation = new Reservation();
		objReservation.setId(rs.getInt("id"));
		objReservation.setComments(rs.getString("comments"));
		objReservation.setTrainer(rs.getString("trainer"));
		objReservation.setTeam(rs.getString("team"));
		objReservation.setPitch(rs.getString("pitch"));	
		objReservation.setSubmitdate(rs.getDate("submit_date"));
		objReservation.setReservationdate(rs.getDate("reservation_date"));

		//Convert dates to string and populate time string value for display purposes		
		SimpleDateFormat sdf=new SimpleDateFormat("E MMM dd HH:mm");
		objReservation.setReservationtime(sdf.format(rs.getTimestamp("reservation_date")));
		objReservation.setSubmittime(sdf.format(rs.getTimestamp("submit_date")));
		
	return objReservation;
	}

}
