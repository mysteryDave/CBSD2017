package net.java.cargotracker.domain.model.flight;

import java.util.List;

public interface FlightRepo {

    Flight find(int number);

    List<Flight> findAll();

    void add(Flight flight);

    void cancel(int flightNo);
    
    List<Integer> getAllFlightIds();
}
