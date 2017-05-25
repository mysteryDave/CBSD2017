package net.java.cargotracker.application.util;

import java.util.*;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.java.cargotracker.domain.model.airport.SampleAirports;
import net.java.cargotracker.domain.model.carrier.SampleCarriers;
import net.java.cargotracker.domain.model.flight.Flight;

/**
 * Loads sample data for demo.
 */
@Singleton
@Startup
public class SampleDataGenerator {

    // TODO See if the logger can be injected.
    private static final Logger logger = Logger.getLogger(
            SampleDataGenerator.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void loadSampleData() {
        logger.info("Loading sample data.");
        unLoadAll(); //  Fail-safe in case of application restart that does not trigger a JPA schema drop.
        loadAirports();
        loadCarriers();
        loadFlights();
    }

    private void unLoadAll() {
        logger.info("Unloading all existing data.");
        // Delete all entities
        // TODO See why cascade delete is not working.
        entityManager.createQuery("Delete from Airport").executeUpdate();
        entityManager.createQuery("Delete from Carrier").executeUpdate();
        entityManager.createQuery("Delete from Flight").executeUpdate();
    }

    private void loadAirports() { //50 default airports for testing.
        logger.info("Loading sample airports.");

        entityManager.persist(SampleAirports.ATLANTA);
        entityManager.persist(SampleAirports.DUBAI);
        entityManager.persist(SampleAirports.GATWICK);
        entityManager.persist(SampleAirports.HEATHROW);
        entityManager.persist(SampleAirports.HONG_KONG);
        entityManager.persist(SampleAirports.LOS_ANGELES);
        entityManager.persist(SampleAirports.LONDON_CITY);
        entityManager.persist(SampleAirports.LUTON);
        entityManager.persist(SampleAirports.STANSTED);
        entityManager.persist(SampleAirports.SOUTHEND);
        entityManager.persist(SampleAirports.BIRMINGHAM);
        entityManager.persist(SampleAirports.MANCHESTER);
        entityManager.persist(SampleAirports.EDINBURGH);
        entityManager.persist(SampleAirports.GLASGOW);
        entityManager.persist(SampleAirports.BRISTOL);
        entityManager.persist(SampleAirports.NEWCASTLE);
        entityManager.persist(SampleAirports.EAST_MIDS);
        entityManager.persist(SampleAirports.BELFAST_INTL);
        entityManager.persist(SampleAirports.LIVERPOOL);
        entityManager.persist(SampleAirports.LEEDS);
        entityManager.persist(SampleAirports.ABERDEEN);
        entityManager.persist(SampleAirports.BELFAST_GB);
        entityManager.persist(SampleAirports.SOUTHAMPTON);
        entityManager.persist(SampleAirports.CARDIFF);
        entityManager.persist(SampleAirports.CHICAGO);
        entityManager.persist(SampleAirports.DALLAS);
        entityManager.persist(SampleAirports.DENVER);
        entityManager.persist(SampleAirports.NEW_YORK_JFK);
        entityManager.persist(SampleAirports.SAN_FRANSISCO);
        entityManager.persist(SampleAirports.CHARLOTTE);
        entityManager.persist(SampleAirports.LAS_VEGAS);
        entityManager.persist(SampleAirports.PHOENIX);
        entityManager.persist(SampleAirports.BEIJING);
        entityManager.persist(SampleAirports.TOKYO);
        entityManager.persist(SampleAirports.PARIS_CDG);
        entityManager.persist(SampleAirports.ISTANBUL);
        entityManager.persist(SampleAirports.FRANKFURT);
        entityManager.persist(SampleAirports.AMSTERDAM);
        entityManager.persist(SampleAirports.SINGAPORE);
        entityManager.persist(SampleAirports.GUANGZHOU);
        entityManager.persist(SampleAirports.JAKARTA);
        entityManager.persist(SampleAirports.BANGKOK);
        entityManager.persist(SampleAirports.MADRID);
        entityManager.persist(SampleAirports.MUNICH);
        entityManager.persist(SampleAirports.ROME);
        entityManager.persist(SampleAirports.BARCELONA);
        entityManager.persist(SampleAirports.SYDNEY);
        entityManager.persist(SampleAirports.MUMBAI);
        entityManager.persist(SampleAirports.SAU_PAULO);
        entityManager.persist(SampleAirports.MEXICO);
    }

    private void loadCarriers() { //30 default airlines for testing.
        logger.info("Loading sample airlines.");

        entityManager.persist(SampleCarriers.EASYJET);
        entityManager.persist(SampleCarriers.BA);
        entityManager.persist(SampleCarriers.FLYBE);
        entityManager.persist(SampleCarriers.VIRGIN);
        entityManager.persist(SampleCarriers.MONARCH);
        entityManager.persist(SampleCarriers.JET2);
        entityManager.persist(SampleCarriers.THOMAS_COOK);
        entityManager.persist(SampleCarriers.LOGAN);
        entityManager.persist(SampleCarriers.AURIGNY);
        entityManager.persist(SampleCarriers.THOMSON);
        entityManager.persist(SampleCarriers.RYANAIR);
        entityManager.persist(SampleCarriers.LUFTHANSA);
        entityManager.persist(SampleCarriers.IBERIA);
        entityManager.persist(SampleCarriers.AIR_FRANCE);
        entityManager.persist(SampleCarriers.KLM);
        entityManager.persist(SampleCarriers.TURKISH);
        entityManager.persist(SampleCarriers.AEROFLOT);
        entityManager.persist(SampleCarriers.SAS);
        entityManager.persist(SampleCarriers.NORWEGIAN);
        entityManager.persist(SampleCarriers.AIR_BERLIN);
        entityManager.persist(SampleCarriers.EMIRATES);
        entityManager.persist(SampleCarriers.UNITED);
        entityManager.persist(SampleCarriers.EAST_CHINA);
        entityManager.persist(SampleCarriers.AIR_CANADA);
        entityManager.persist(SampleCarriers.ANA);
        entityManager.persist(SampleCarriers.AA);
        entityManager.persist(SampleCarriers.SOUTHWEST);
        entityManager.persist(SampleCarriers.DELTA);
        entityManager.persist(SampleCarriers.SOUTH_CHINA);
        entityManager.persist(SampleCarriers.AIR_CHINA);
    }

    private void loadFlights() {
        logger.info("Loading sample flights.");

        Calendar timeNow = new GregorianCalendar();
        timeNow.set(Calendar.MILLISECOND, 0);
        timeNow.set(Calendar.SECOND, 0);
        timeNow.set(Calendar.MINUTE, 0);
        final long HOUR = 60 * 60 * 1000;

        entityManager.persist(new Flight( SampleCarriers.BA, SampleAirports.HEATHROW, SampleAirports.EDINBURGH,
                new Date(timeNow.getTimeInMillis() + (24 * HOUR)), new Date(timeNow.getTimeInMillis() + 1800000 + (25 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.BA, SampleAirports.GATWICK, SampleAirports.PARIS_CDG,
                new Date(timeNow.getTimeInMillis() + 1800000 + (28 * HOUR)), new Date(timeNow.getTimeInMillis() + (30 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.LUFTHANSA, SampleAirports.FRANKFURT, SampleAirports.HEATHROW,
                new Date(timeNow.getTimeInMillis() + (40 * HOUR)), new Date(timeNow.getTimeInMillis() + 1800000 + (43 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.AA, SampleAirports.DENVER, SampleAirports.NEW_YORK_JFK,
                new Date(timeNow.getTimeInMillis() + 1800000 + (36 * HOUR)), new Date(timeNow.getTimeInMillis() + (40 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.EMIRATES, SampleAirports.DUBAI, SampleAirports.GATWICK,
                new Date(timeNow.getTimeInMillis() + (32 * HOUR)), new Date(timeNow.getTimeInMillis() + (39 * HOUR))));

        entityManager.persist(new Flight( SampleCarriers.BA, SampleAirports.GLASGOW, SampleAirports.EAST_MIDS,
                new Date(timeNow.getTimeInMillis() + (48 * HOUR)), new Date(timeNow.getTimeInMillis() + (49 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.BA, SampleAirports.BIRMINGHAM, SampleAirports.MADRID,
                new Date(timeNow.getTimeInMillis() + 1800000 + (38 * HOUR)), new Date(timeNow.getTimeInMillis() + (41 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.LUFTHANSA, SampleAirports.AMSTERDAM, SampleAirports.FRANKFURT,
                new Date(timeNow.getTimeInMillis() + (20 * HOUR)), new Date(timeNow.getTimeInMillis() + 1800000 + (22 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.AA, SampleAirports.ATLANTA, SampleAirports.CHICAGO,
                new Date(timeNow.getTimeInMillis() + 1800000 + (31 * HOUR)), new Date(timeNow.getTimeInMillis() + (34 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.EMIRATES, SampleAirports.HEATHROW, SampleAirports.DUBAI,
                new Date(timeNow.getTimeInMillis() + (42 * HOUR)), new Date(timeNow.getTimeInMillis() + (49 * HOUR))));

        entityManager.persist(new Flight( SampleCarriers.BA, SampleAirports.HEATHROW, SampleAirports.MUMBAI,
                new Date(timeNow.getTimeInMillis() + (58 * HOUR)), new Date(timeNow.getTimeInMillis() + (66 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.AIR_CHINA, SampleAirports.HONG_KONG, SampleAirports.BEIJING,
                new Date(timeNow.getTimeInMillis() + 1800000 + (25 * HOUR)), new Date(timeNow.getTimeInMillis() + (28 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.LUFTHANSA, SampleAirports.FRANKFURT, SampleAirports.MUNICH,
                new Date(timeNow.getTimeInMillis() + (50 * HOUR)), new Date(timeNow.getTimeInMillis() + 1800000 + (51 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.AA, SampleAirports.CHARLOTTE, SampleAirports.DALLAS,
                new Date(timeNow.getTimeInMillis() + 1800000 + (45 * HOUR)), new Date(timeNow.getTimeInMillis() + (49 * HOUR))));
        entityManager.persist(new Flight( SampleCarriers.AIR_FRANCE, SampleAirports.PARIS_CDG, SampleAirports.NEW_YORK_JFK,
                new Date(timeNow.getTimeInMillis() + (45 * HOUR)), new Date(timeNow.getTimeInMillis() + (54 * HOUR))));
    }
}
