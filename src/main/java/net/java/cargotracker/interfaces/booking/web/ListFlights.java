package net.java.cargotracker.interfaces.booking.web;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles listing flights.
 *
 * Operates against a dedicated service facade.
 *
 * ToDo: include filters for different views.
 *
 */

@Named
@RequestScoped
public class ListFlights {

    private List<Flight> allFlights;
    private List<Airport> allPorts;

    @Inject
    private AirServiceFacade airService;

    public List<Flight> getFlights() {
        return allFlights;
    }

    public List<Airport> getAirports() {
        return allPorts;
    }

    @PostConstruct
    public void init() {
        allFlights = airService.listAllFlights();
        allPorts = airService.listAirports();
    }

}
