package net.java.cargotracker.interfaces.booking.facade;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.carrier.Carrier;
import net.java.cargotracker.domain.model.flight.Flight;

import java.util.Date;
import java.util.List;

/**
 * This facade shields the domain layer - model, services, repositories - from
 * concerns about such things as the user interface and remoting.
 */
public interface AirServiceFacade {

    void addFlight(int flightNo, Airport from, Date departs, Airport to, Date arrives);

    Flight getFlight(int flightNo);

    boolean cxlFlight(int flightNo);

    void bookSeat(int flightNo, String flyer, boolean isBusinessClass);

    boolean cxlBook(int flightNo, String flyer, boolean isBusinessClass);

    List<Airport> listAirports();

    List<Carrier> listCarriers();

    List<Flight> listAllFlights();
}
