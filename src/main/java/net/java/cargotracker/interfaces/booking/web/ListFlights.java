package net.java.cargotracker.interfaces.booking.web;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.carrier.Carrier;
import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private List<Carrier> allLines;

    @Inject
    private AirServiceFacade airService;

    public List<Flight> getAllFlights() {
        return allFlights;
    }

    public List<Airport> getAllPorts() {
        return allPorts;
    }

    public List<Carrier> getAllLines() { return allLines; }

    @PostConstruct
    public void init() {
        allFlights = airService.listAllFlights();
        allPorts = airService.listAirports();
        allLines = airService.listCarriers();
    }

    public void cancelFlight(int number) {
        airService.cxlFlight(number);
    }


}
