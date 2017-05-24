package net.java.cargotracker.interfaces.booking.facade.internal;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.airport.AirportRepo;
import net.java.cargotracker.domain.model.carrier.Carrier;
import net.java.cargotracker.domain.model.carrier.CarrierRepo;
import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.domain.model.flight.FlightRepo;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class SpecialAirService implements AirServiceFacade, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AirportRepo airportRepo;
    @Inject
    private CarrierRepo carrierRepo;
    @Inject
    private FlightRepo flightRepo;

    @Override
    public void addFlight(int flightNo, Airport from, Date departs, Airport to, Date arrives) {

    }

    public Flight getFlight(int flightNo) {
        return flightRepo.find(flightNo);
    }

    @Override
    public boolean cxlFlight(int flightNo) {
        Flight cxlFlight = flightRepo.find(flightNo);
        flightRepo.cancel(flightNo);
        return (cxlFlight != null);
    }

    @Override
    public void bookSeat(int flightNo, String flyer, boolean isBusinessClass) {
      //TODO
    }

    @Override
    public boolean cxlBook(int flightNo, String flyer, boolean isBusinessClass) {
        return false;
    }

    @Override
    public List<Airport> listAirports() {
        List<Airport> allPorts = airportRepo.findAll();
        return allPorts;
    }

    @Override
    public List<Carrier> listCarriers() {
        List<Carrier> allLines = carrierRepo.findAll();
        return allLines;
    }

    @Override
    public List<Flight> listAllFlights() {
        return flightRepo.findAll();
    }

}
