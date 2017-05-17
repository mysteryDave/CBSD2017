package net.java.cargotracker.domain.model.airport;

import java.util.List;

public interface AirportRepo {

    Airport find(String code);

    List<Airport> findAll();
}
