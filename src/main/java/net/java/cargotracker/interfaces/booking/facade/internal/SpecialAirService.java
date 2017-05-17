package net.java.cargotracker.interfaces.booking.facade.internal;

import net.java.cargotracker.application.BookingService;
import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.cargo.Cargo;
import net.java.cargotracker.domain.model.cargo.CargoRepository;
import net.java.cargotracker.domain.model.cargo.Itinerary;
import net.java.cargotracker.domain.model.cargo.TrackingId;
import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.domain.model.location.Location;
import net.java.cargotracker.domain.model.location.LocationRepository;
import net.java.cargotracker.domain.model.location.UnLocode;
import net.java.cargotracker.domain.model.voyage.VoyageRepository;
import net.java.cargotracker.interfaces.booking.facade.AirServiceFacade;
import net.java.cargotracker.interfaces.booking.facade.BookingServiceFacade;
import net.java.cargotracker.interfaces.booking.facade.dto.CargoRoute;
import net.java.cargotracker.interfaces.booking.facade.dto.RouteCandidate;
import net.java.cargotracker.interfaces.booking.facade.internal.assembler.CargoRouteDtoAssembler;
import net.java.cargotracker.interfaces.booking.facade.internal.assembler.ItineraryCandidateDtoAssembler;
import net.java.cargotracker.interfaces.booking.facade.internal.assembler.LocationDtoAssembler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class SpecialAirService implements AirServiceFacade, Serializable {

    /*
    private static final long serialVersionUID = 1L;
    @Inject
    private BookingService bookingService;
    @Inject
    private LocationRepository locationRepository;
    @Inject
    private CargoRepository cargoRepository;
    @Inject
    private VoyageRepository voyageRepository;
*/

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
        return null;
    }

    @Override
    public List<Flight> listAllFlights() {
        return null;
    }

}
