Component Based Software Development Coursework
David Tucker
13126049
May 2015

Design.vsdx - Microsoft Visual Studio 2013 document containing:
	Webpage layout design
	UML class / entity relationship model diagram



Intention was to have a basic view for all users that listed flights and user dependent functionality to filter, search, add, remove or book.

Project code is taken from Cargo-Tracker java EE 7 example and modified to (begin) meeting the coursework specification.

Currently this has acheived: creating entity models for airports, airlines and flights, and displaying a list of available flights.

Completed steps:
  Model airport entity.
  Model carrier(airline) entity.
  Model flight entity - linking two airports via a carrier.
  Display flights.

Remaining steps to be done:
  Complete functionality to allow reservation manager to cancel and add flights.
  Build functionality for users to register themselves.
    Model user entity
    Add username/password login to replace customer/manager links
  Include booking functionality on flights:
    embedd aircraft (abstract) class with capacity information simplePlane implementation with 10 business & 90 economy
    model booking entity
    embedd container of booking objects to flight
      (booking links flights to user in many many relationship, see initial UML class/relationship)
      Flight many<>many Passenger -> Flight many<>one Booking one<>many Passenger
    add functionality for user to create or cancel a booking
    modify cancel flight functionality to cancel bookings embedded in flight