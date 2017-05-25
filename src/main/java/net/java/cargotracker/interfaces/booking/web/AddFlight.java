package net.java.cargotracker.interfaces.booking.web;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.carrier.Carrier;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;
import org.primefaces.context.RequestContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

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
        //Derive date/times
        Calendar startDate = new GregorianCalendar();
        Calendar flyTime = new GregorianCalendar();
        startDate.setTime(flyDate);
        flyTime.setTime(startTime);

        startDate.set(Calendar.HOUR, flyTime.get(Calendar.HOUR));
        startDate.set(Calendar.MINUTE, flyTime.get(Calendar.MINUTE));
        startDate.set(Calendar.AM_PM, flyTime.get(Calendar.AM_PM));
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);

        Calendar endDate = new GregorianCalendar();
        endDate.setTime(startDate.getTime());
        flyTime.setTime(endTime);

        endDate.set(Calendar.HOUR, flyTime.get(Calendar.HOUR));
        endDate.set(Calendar.MINUTE, flyTime.get(Calendar.MINUTE));
        endDate.set(Calendar.AM_PM, flyTime.get(Calendar.AM_PM));
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);

        if(startDate.getTimeInMillis() > endDate.getTimeInMillis()) {
            endDate.set(Calendar.DATE, startDate.get(Calendar.DATE) + 1);
        }

        //add flight
        airService.addFlight( airService.findCarrier(getAirlineCode()),
            airService.findAirport(getFromPort()),
            new Date(startDate.getTimeInMillis()),
            airService.findAirport(getToPort()),
            new Date(endDate.getTimeInMillis()));

        RequestContext.getCurrentInstance().closeDialog("");
    }

}
