The idea of this application is to create a reservation app for the local soccer club.
The soccer club has 4 training pitches and this app allows a trainer to reserve a training pitch for a specific team on a specific date.

The following logins are available

Username / Password
admin / admin2012
traineru7 / wghu72012
traineru8 / wghu82012
traineru9 / wghu92012
traineru10 / wghu102012
traineru1 / wghu112012
traineru12 / wghu122012

traineru8 / wghu82012 has some relevant reservation data in CloundFoundry (http://reservationapp.cloudfoundry.com)

The reservation date is a mandatory field. (Validation test is done on the page)

A pitch can be only reserved once on a specific timeslot (timeslot = one hour) - SQL CONSTRAINT on reservationdate and pitch

A trainer can only cancel his/her own reservations using the cancel button. Cancel button only appears on own reservations.

The app lists only all reservations from today and in the future (>= current_date)

//**************************************************
CLOUDFOUNDRY details
Available on http://reservationapp.cloudfoundry.com
Service Name :dbResCFDBS (POSTGRESQL Database)
//**************************************************
REST API details

// get   : curl -i --user traineru7:wghu72012 http://localhost:8080/reservationapp/rest/reservation
// create: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X POST -d '{"comments":"Weekly Training","pitch":"Main pitch","team":"u7","reservationdate":"06/01/2012 10:00"}' http://localhost:8080/reservationapp/rest/reservation
// get   : curl -i --user traineru7:wghu72012  http://localhost:8080/reservationapp/rest/reservation/1
// delete: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X DELETE http://localhost:8080/reservationapp/rest/reservation/1
