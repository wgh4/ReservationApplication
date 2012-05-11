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

The reservation date is a mandatory field.

A pitch can be only reservered once on a specific timeslot (timeslot = one hour) - SQL CONSTRAINT on reservationdate and pitch

The app lists only all reservations from today and in the future

CLOUDFOUNDRY details
Service Name dbResCFDBS

REST API details

// get   : curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" http://localhost:8080/reservationapp/rest/reservation
// create: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X POST -d '{"comment": "buy milk"}' http://localhost:8080/reservationapp/rest/reservation
// get   : curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" http://localhost:8080/reservationapp/rest/reservation/1
// update: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X PUT -d '{"comment": "buy milk", "done": true}' http://localhost:8080/reservationapp/rest/reservation/1
// delete: curl -i --user traineru7:wghu72012 -H "Content-Type: application/json" -X DELETE http://localhost:8080/reservationapp/rest/reservation/1
