package net.java.cargotracker.interfaces.booking.socket;

import net.java.cargotracker.domain.model.cargo.Cargo;
import net.java.cargotracker.domain.model.cargo.RouteSpecification;
import net.java.cargotracker.domain.model.cargo.TrackingId;
import net.java.cargotracker.domain.model.location.Location;
import net.java.cargotracker.infrastructure.events.cdi.CargoInspected;
import javax.enterprise.event.Observes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;


/**
 * Structure for tracking observed cargo.
 *
 * @author Den
 */

@Named
@ApplicationScoped
public class ObservedCargoReporter implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Cargo> observedCargo = new ArrayList<>();

    public ObservedCargoReporter() {
        System.out.println("Created CARGO observer");
        observedCargo.add(new Cargo(new TrackingId("XYZ000"),new RouteSpecification(Location.UNKNOWN,Location.UNKNOWN,new Date(1494686394))));
    }

    public void onCargoInspected(@Observes @CargoInspected Cargo cargo) {
       observedCargo.add(cargo);
       System.out.println("ObservedCargoReporter sees " + cargo.toString());
    }

    public List<Cargo> getObservedCargo() {
        return observedCargo;
    }
}