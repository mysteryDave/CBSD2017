package net.java.cargotracker.infrastructure.persistence.jpa;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.airport.AirportRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class JpaAirportRepository implements AirportRepo, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Airport find(String code) {
        return entityManager.createNamedQuery("Airport.findByCode",
                Airport.class).setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public List<Airport> findAll() {
        return entityManager.createNamedQuery("Airport.findAll", Airport.class)
                .getResultList();
    }
}
