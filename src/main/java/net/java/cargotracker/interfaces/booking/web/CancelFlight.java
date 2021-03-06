package net.java.cargotracker.interfaces.booking.web;

import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;
import org.primefaces.context.RequestContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Allows manager to cancel a flight
 *
 * Should check and cancel any bookings on the flight first
 *
 */

@Named
@ViewScoped
public class CancelFlight implements Serializable {

    private static final long serialVersionUID = 1L;

    private int flightNo;
    private Flight flight;

    @Inject
    private AirServiceFacade airService;

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int newNo) {
        this.flightNo = newNo;
    }

    public Flight getFlight() {
        return flight;
    }

    public void cancelFlight() {
        airService.cxlFlight(flight.getNumber());
        RequestContext.getCurrentInstance().closeDialog("");
    }

    public void load() {
        flight = airService.getFlight(flightNo);
    }

}
