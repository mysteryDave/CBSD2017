package net.java.cargotracker.domain.model.airport;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import net.java.cargotracker.domain.model.location.Location;
import net.java.cargotracker.domain.model.location.UnLocode;
import org.apache.commons.lang3.Validate;

/**
 * Flights must depart from an airport and go to (another) airport
 *
 * It is uniquely identified by a IATA location code consisting of three characters.
 */

@Entity
public class Airport implements Serializable {

    private static final String IATA_PORT_PATTERN = "[A-Z]{3}";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Pattern(regexp = IATA_PORT_PATTERN)
    private String sIATAcode;

    @NotNull
    private String name;

    public Airport() {}

    public Airport(String code, String name) {
        Validate.notNull(code);
        Validate.matchesPattern(IATA_PORT_PATTERN,code);
        Validate.notNull(name);

        this.sIATAcode = code;
        this.name = name;
    }

    /**
     * @return IATA code for this location, e.g. "LGW".
     */
    public String getIATAcode() {
        return sIATAcode;
    }

    /**
     * @return Actual name of this location, e.g. "London Gatwick".
     */
    public String getName() {
        return name;
    }

    /**
     * @param object to compare
     * @return Since this is an entiy this will be true iff UN locodes are
     * equal.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof Airport)) {
            return false;
        }
        Airport other = (Airport) object;
        return sameIdentityAs(other);
    }

    public boolean sameIdentityAs(Airport other) {
        return this.sIATAcode.equalsIgnoreCase(other.sIATAcode);
    }

    @Override
    public String toString() {
        return name + " [" + sIATAcode + "]";
    }
}