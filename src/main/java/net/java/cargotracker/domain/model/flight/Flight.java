/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package net.java.cargotracker.domain.model.flight;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import net.java.cargotracker.domain.model.cargo.*;
import net.java.cargotracker.domain.model.handling.HandlingEvent;
import net.java.cargotracker.domain.model.handling.HandlingHistory;
import net.java.cargotracker.domain.model.location.Location;
import net.java.cargotracker.domain.shared.DomainObjectUtils;
import org.apache.commons.lang3.Validate;

/**
 * A Flight. This is the central class in the domain model.
 * Each has a unique code in range 000 - 999.
 * Each has a carrier and an aircraft (to inject maximum capacity.)
 * Each has a departure airport and a destination airport.
 * Each has a scheduled departure time and scheduled arrival time.
 *
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Flight.findAll",
            query = "Select f from Flight f"),
    @NamedQuery(name = "Cargo.findByFlightNumber",
            query = "Select f from Flight f where f.number = :number"),
    @NamedQuery(name = "Cargo.getAllFlightNumbers",
            query = "Select f.number from Flight f") })
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    // Auto-generated surrogate key
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int number;

    public Flight() {}

    public Flight(int number) {
        Validate.notNull(number, "Tracking ID is required");

        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(Flight)
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Flight other = (Flight) object;
        return sameIdentityAs(other);
    }

    private boolean sameIdentityAs(Flight other) {
        return (other != null && number == other.number);
    }

    @Override
    public String toString() {
        return String.format("%d03", number);
    }
}
