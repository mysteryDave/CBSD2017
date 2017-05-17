package net.java.cargotracker.interfaces.booking.facade;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.flight.Flight;

import java.util.Date;
import java.util.List;

/**
 * This facade shields the domain layer - model, services, repositories - from
 * concerns about such things as the user interface and remoting.
 */
public interface AirServiceFacade {

    void addFlight(short flightNo, Airport from, Date departs, Airport to, Date arrives);

    boolean cxlFlight(short flightNo);

    void bookSeat(short flightNo, String flyer, boolean isBusinessClass);

    boolean cxlBook(short flightNo, String flyer, boolean isBusinessClass);

    List<Airport> listAirports();

    List<Flight> listAllFlights();
}
