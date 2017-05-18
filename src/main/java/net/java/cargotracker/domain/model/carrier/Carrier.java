package net.java.cargotracker.domain.model.carrier;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.Validate;

/**
 * Flights must be operated by an carrier (airline)
 *
 * It is uniquely identified by a IATA airline code consisting of two alphanumeric characters.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Carrier.findAll", query = "Select l from Carrier l"),
        @NamedQuery(name = "Carrier.findByCode",
                query = "Select l from Carrier l where l.code = :code")})
public class Carrier implements Serializable {

    private static final String IATA_LINE_PATTERN = "[A-Z0-9]{2}";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    @Pattern(regexp = IATA_LINE_PATTERN)
    private String code;

    @NotNull
    private String name;

    public Carrier() {}

    public Carrier(String code, String name) {
        Validate.notNull(code);
        Validate.matchesPattern(code,IATA_LINE_PATTERN);
        Validate.notNull(name);

        this.code = code;
        this.name = name;
    }

    /**
     * @return IATA code for this carrier, e.g. "BA".
     */
    public String getCode() { return code; }

    /**
     * @return Actual name of this carrier, e.g. "British Airways".
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
        if (!(object instanceof Carrier)) {
            return false;
        }
        Carrier other = (Carrier) object;
        return sameIdentityAs(other);
    }

    public boolean sameIdentityAs(Carrier other) {
        return this.code.equalsIgnoreCase(other.code);
    }

    @Override
    public String toString() {
        return name + " [" + code + "]";
    }
}