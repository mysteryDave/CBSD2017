package net.java.cargotracker.interfaces.booking.web;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.carrier.Carrier;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;
import org.primefaces.context.RequestContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Allows manager to add a new flight
 *
 */

@Named
@ViewScoped
public class AddFlight implements Serializable {

    private static final long serialVersionUID = 1L;

    //Better to use a flight object here?
    private int number;
    private String airlineCode;
    private String fromPort;
    private String toPort;
    private Date flyDate;
    private Date startTime;
    private Date endTime;

    @Inject
    private AirServiceFacade airService;

    //Getters, Setters
    public void setAirlineCode(String code) { airlineCode = code; }

    public String getAirlineCode() { return airlineCode; }

    public void setFromPort(String airport) { fromPort = airport; }

    public String getFromPort() { return fromPort; }

    public void setToPort(String airport) { toPort = airport; }

    public String getToPort() { return toPort; }

    public void setFlyDate(Date date) { flyDate = date; }

    public Date getFlyDate() { return flyDate; }

    public void setStartTime(Date time) { startTime = time; }

    public Date getStartTime() { return startTime; }

    public void setEndTime(Date date) { endTime = date; }

    public Date getEndTime() { return endTime; }

    public List<String> autoCarrierCode(String query) {
        List<Carrier> airlines = airService.listCarriers();
        List<String> airlineCodes = new ArrayList<>(airlines.size());
        for (Carrier airline : airlines) { airlineCodes.add(airline.getCode()); }
        return airlineCodes;
    }

    public List<String> autoPortCode(String query) {
        List<Airport> ports = airService.listAirports();
        List<String> portCodes = new ArrayList<>(ports.size());
        for (Airport airport : ports) { portCodes.add(airport.getCode()); }
        return portCodes;
    }

    public void add() {
        //Add the flight
    }

}
