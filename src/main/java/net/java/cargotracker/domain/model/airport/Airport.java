package net.java.cargotracker.domain.model.airport;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.Validate;

/**
 * Flights must depart from an airport and go to (another) airport
 *
 * It is uniquely identified by a IATA location code consisting of three characters.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Airport.findAll", query = "Select l from Airport l"),
        @NamedQuery(name = "Airport.findByCode",
                query = "Select l from Airport l where l.code = :code")})
public class Airport implements Serializable {

    private static final String IATA_PORT_PATTERN = "[A-Z]{3}";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    @Pattern(regexp = IATA_PORT_PATTERN)
    private String code;

    @NotNull
    private String name;

    public Airport() {}

    public Airport(String code, String name) {
        Validate.notNull(code);
        Validate.matchesPattern(code,IATA_PORT_PATTERN);
        Validate.notNull(name);

        this.code = code;
        this.name = name;
    }

    /**
     * @return IATA code for this location, e.g. "LGW".
     */
    public String getCode() { return code; }

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
        return this.code.equalsIgnoreCase(other.code);
    }

    @Override
    public String toString() {
        return name + " [" + code + "]";
    }
}