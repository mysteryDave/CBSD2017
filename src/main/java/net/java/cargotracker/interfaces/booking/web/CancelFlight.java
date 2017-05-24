package net.java.cargotracker.interfaces.booking.web;

import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;
import net.java.cargotracker.interfaces.booking.facade.BookingServiceFacade;
import net.java.cargotracker.interfaces.booking.facade.dto.CargoRoute;
import net.java.cargotracker.interfaces.booking.facade.dto.Location;
import org.primefaces.context.RequestContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        airService.cxlFlight(flightNo);
        RequestContext.getCurrentInstance().closeDialog("DONE");
    }

    public void load() {
        flight = airService.getFlight(flightNo);
    }

}
