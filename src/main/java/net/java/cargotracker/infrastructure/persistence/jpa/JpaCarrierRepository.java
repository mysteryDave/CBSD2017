package net.java.cargotracker.infrastructure.persistence.jpa;

import net.java.cargotracker.domain.model.carrier.Carrier;
import net.java.cargotracker.domain.model.carrier.CarrierRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class JpaCarrierRepository implements CarrierRepo, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Carrier find(String code) {
        return entityManager.createNamedQuery("Carrier.findByCode",
                Carrier.class).setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public List<Carrier> findAll() {
        return entityManager.createNamedQuery("Carrier.findAll", Carrier.class)
                .getResultList();
    }
}
