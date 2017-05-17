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
import javax.persistence.*;

import net.java.cargotracker.domain.model.cargo.*;
import net.java.cargotracker.domain.model.handling.HandlingEvent;
import net.java.cargotracker.domain.model.handling.HandlingHistory;
import net.java.cargotracker.domain.model.location.Location;
import net.java.cargotracker.domain.shared.DomainObjectUtils;
import org.apache.commons.lang3.Validate;

/**
 * A flight. This is the central class.
 *
 * A flight is identified by a unique 3 digit numeric tracking id.
 * It should always has a departure airport and date/time.
 * It should always have an arrival airport and date/time.
 *
 */
@Entity
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    // Auto-generated key
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "flight_number", unique = true, updatable = false)
    private short number;

    public Flight() {} // Nothing to initialize.

    public Flight(short number) {
        Validate.notNull(number, "Flight Number Required");
        this.number = number;
    }

    public short getNumber() {
        return number;
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(Flight)
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Flight other = (Flight) object;
        return sameIdentityAs(other);
    }

    private boolean sameIdentityAs(Flight other) {
        return other != null && number == other.number;
    }

    @Override
    public String toString() {
        return String.format("%03", number);
    }
}
