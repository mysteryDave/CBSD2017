package net.java.cargotracker.domain.model.carrier;

import java.util.List;

public interface CarrierRepo {

    Carrier find(String code);

    List<Carrier> findAll();
}
