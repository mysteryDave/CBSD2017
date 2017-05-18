package net.java.cargotracker.interfaces.booking.facade.internal;


import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.airport.AirportRepo;
import net.java.cargotracker.domain.model.carrier.Carrier;
import net.java.cargotracker.domain.model.carrier.CarrierRepo;
import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;

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

    @Override
    public void addFlight(short flightNo, Airport from, Date departs, Airport to, Date arrives) {

    }

    @Override
    public boolean cxlFlight(short flightNo) {
        return false;
    }

    @Override
    public void bookSeat(short flightNo, String flyer, boolean isBusinessClass) {

    }

    @Override
    public boolean cxlBook(short flightNo, String flyer, boolean isBusinessClass) {
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
        return null;
    }

}
