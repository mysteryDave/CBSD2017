package net.java.cargotracker.application.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.java.cargotracker.domain.model.airport.Airport;
import net.java.cargotracker.domain.model.airport.SampleAirports;
import net.java.cargotracker.domain.model.cargo.Cargo;
import net.java.cargotracker.domain.model.cargo.Itinerary;
import net.java.cargotracker.domain.model.cargo.Leg;
import net.java.cargotracker.domain.model.cargo.RouteSpecification;
import net.java.cargotracker.domain.model.cargo.TrackingId;
import net.java.cargotracker.domain.model.carrier.Carrier;
import net.java.cargotracker.domain.model.carrier.SampleCarriers;
import net.java.cargotracker.domain.model.flight.Flight;
import net.java.cargotracker.domain.model.handling.CannotCreateHandlingEventException;
import net.java.cargotracker.domain.model.handling.HandlingEvent;
import net.java.cargotracker.domain.model.handling.HandlingEventFactory;
import net.java.cargotracker.domain.model.handling.HandlingEventRepository;
import net.java.cargotracker.domain.model.handling.HandlingHistory;
import net.java.cargotracker.domain.model.location.SampleLocations;
import net.java.cargotracker.domain.model.voyage.SampleVoyages;

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
    @Inject
    private HandlingEventFactory handlingEventFactory;
    @Inject
    private HandlingEventRepository handlingEventRepository;

    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void loadSampleData() {
        logger.info("Loading sample data.");
        unLoadAll(); //  Fail-safe in case of application restart that does not trigger a JPA schema drop.
        loadSampleLocations();
        loadSampleVoyages();
        loadSampleCargos();
        loadAirports();
        loadCarriers();
        loadFlights();
    }

    private void unLoadAll() {
        logger.info("Unloading all existing data.");
        // In order to remove handling events, must remove refrences in cargo.
        // Dropping cargo first won't work since handling events have references
        // to it.
        // TODO See if there is a better way to do this.
        List<Cargo> cargos = entityManager.createQuery("Select c from Cargo c",
                Cargo.class).getResultList();
        for (Cargo cargo : cargos) {
            cargo.getDelivery().setLastEvent(null);
            entityManager.merge(cargo);
        }

        // Delete all entities
        // TODO See why cascade delete is not working.
        entityManager.createQuery("Delete from HandlingEvent").executeUpdate();
        entityManager.createQuery("Delete from Leg").executeUpdate();
        entityManager.createQuery("Delete from Cargo").executeUpdate();
        entityManager.createQuery("Delete from CarrierMovement").executeUpdate();
        entityManager.createQuery("Delete from Voyage").executeUpdate();
        entityManager.createQuery("Delete from Location").executeUpdate();
        entityManager.createQuery("Delete from Airport").executeUpdate();
        entityManager.createQuery("Delete from Carrier").executeUpdate();
        entityManager.createQuery("Delete from Flight").executeUpdate();
    }

    private void loadSampleLocations() {
        logger.info("Loading sample locations.");

        entityManager.persist(SampleLocations.HONGKONG);
        entityManager.persist(SampleLocations.MELBOURNE);
        entityManager.persist(SampleLocations.STOCKHOLM);
        entityManager.persist(SampleLocations.HELSINKI);
        entityManager.persist(SampleLocations.CHICAGO);
        entityManager.persist(SampleLocations.TOKYO);
        entityManager.persist(SampleLocations.HAMBURG);
        entityManager.persist(SampleLocations.SHANGHAI);
        entityManager.persist(SampleLocations.ROTTERDAM);
        entityManager.persist(SampleLocations.GOTHENBURG);
        entityManager.persist(SampleLocations.HANGZOU);
        entityManager.persist(SampleLocations.NEWYORK);
        entityManager.persist(SampleLocations.DALLAS);
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

        entityManager.persist(new Flight(101, SampleCarriers.BA, SampleAirports.HEATHROW, SampleAirports.EDINBURGH));
        entityManager.persist(new Flight(999, SampleCarriers.BA, SampleAirports.GATWICK, SampleAirports.PARIS_CDG));
        entityManager.persist(new Flight(299, SampleCarriers.LUFTHANSA, SampleAirports.FRANKFURT, SampleAirports.HEATHROW));
        entityManager.persist(new Flight(911, SampleCarriers.AA, SampleAirports.DENVER, SampleAirports.NEW_YORK_JFK));
        entityManager.persist(new Flight(8, SampleCarriers.EMIRATES, SampleAirports.DUBAI, SampleAirports.GATWICK));
    }

    private void loadSampleVoyages() {
        logger.info("Loading sample voyages.");

        entityManager.persist(SampleVoyages.HONGKONG_TO_NEW_YORK);
        entityManager.persist(SampleVoyages.NEW_YORK_TO_DALLAS);
        entityManager.persist(SampleVoyages.DALLAS_TO_HELSINKI);
        entityManager.persist(SampleVoyages.HELSINKI_TO_HONGKONG);
        entityManager.persist(SampleVoyages.DALLAS_TO_HELSINKI_ALT);
    }

    private void loadSampleCargos() {
        logger.info("Loading sample cargo data.");

        // Cargo ABC123
        TrackingId trackingId1 = new TrackingId("ABC123");

        RouteSpecification routeSpecification1 = new RouteSpecification(
                SampleLocations.HONGKONG, SampleLocations.HELSINKI,
                DateUtil.toDate("2015-03-15"));
        Cargo abc123 = new Cargo(trackingId1, routeSpecification1);

        Itinerary itinerary1 = new Itinerary(Arrays.asList(
                new Leg(SampleVoyages.HONGKONG_TO_NEW_YORK,
                        SampleLocations.HONGKONG, SampleLocations.NEWYORK,
                        DateUtil.toDate("2015-03-02"),
                        DateUtil.toDate("2015-03-05")),
                new Leg(SampleVoyages.NEW_YORK_TO_DALLAS,
                        SampleLocations.NEWYORK,
                        SampleLocations.DALLAS,
                        DateUtil.toDate("2015-03-06"),
                        DateUtil.toDate("2015-03-08")),
                new Leg(SampleVoyages.DALLAS_TO_HELSINKI,
                        SampleLocations.DALLAS,
                        SampleLocations.HELSINKI,
                        DateUtil.toDate("2015-03-09"),
                        DateUtil.toDate("2015-03-12"))));
        abc123.assignToRoute(itinerary1);

        entityManager.persist(abc123);

        try {
            HandlingEvent event1 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-03-01"), trackingId1, null,
                    SampleLocations.HONGKONG.getUnLocode(),
                    HandlingEvent.Type.RECEIVE);
            entityManager.persist(event1);

            HandlingEvent event2 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-03-02"), trackingId1,
                    SampleVoyages.HONGKONG_TO_NEW_YORK.getVoyageNumber(),
                    SampleLocations.HONGKONG.getUnLocode(),
                    HandlingEvent.Type.LOAD);
            entityManager.persist(event2);

            HandlingEvent event3 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-03-05"), trackingId1,
                    SampleVoyages.HONGKONG_TO_NEW_YORK.getVoyageNumber(),
                    SampleLocations.NEWYORK.getUnLocode(),
                    HandlingEvent.Type.UNLOAD);
            entityManager.persist(event3);
        } catch (CannotCreateHandlingEventException e) {
            throw new RuntimeException(e);
        }

        HandlingHistory handlingHistory1
                = handlingEventRepository.lookupHandlingHistoryOfCargo(trackingId1);
        abc123.deriveDeliveryProgress(handlingHistory1);

        entityManager.persist(abc123);

        // Cargo JKL567
        TrackingId trackingId2 = new TrackingId("JKL567");

        RouteSpecification routeSpecification2 = new RouteSpecification(
                SampleLocations.HANGZOU, SampleLocations.STOCKHOLM,
                DateUtil.toDate("2015-03-18"));
        Cargo jkl567 = new Cargo(trackingId2, routeSpecification2);

        Itinerary itinerary2 = new Itinerary(Arrays.asList(
                new Leg(SampleVoyages.HONGKONG_TO_NEW_YORK,
                        SampleLocations.HANGZOU, SampleLocations.NEWYORK,
                        DateUtil.toDate("2015-03-03"),
                        DateUtil.toDate("2015-03-05")),
                new Leg(SampleVoyages.NEW_YORK_TO_DALLAS,
                        SampleLocations.NEWYORK, SampleLocations.DALLAS,
                        DateUtil.toDate("2015-03-06"),
                        DateUtil.toDate("2015-03-08")),
                new Leg(SampleVoyages.DALLAS_TO_HELSINKI, SampleLocations.DALLAS,
                        SampleLocations.STOCKHOLM,
                        DateUtil.toDate("2015-03-09"),
                        DateUtil.toDate("2015-03-11"))));
        jkl567.assignToRoute(itinerary2);

        entityManager.persist(jkl567);

        try {
            HandlingEvent event1 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-03-01"), trackingId2, null,
                    SampleLocations.HANGZOU.getUnLocode(),
                    HandlingEvent.Type.RECEIVE);
            entityManager.persist(event1);

            HandlingEvent event2 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-03-03"), trackingId2,
                    SampleVoyages.HONGKONG_TO_NEW_YORK.getVoyageNumber(),
                    SampleLocations.HANGZOU.getUnLocode(),
                    HandlingEvent.Type.LOAD);
            entityManager.persist(event2);

            HandlingEvent event3 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-03-05"), trackingId2,
                    SampleVoyages.HONGKONG_TO_NEW_YORK.getVoyageNumber(),
                    SampleLocations.NEWYORK.getUnLocode(),
                    HandlingEvent.Type.UNLOAD);
            entityManager.persist(event3);

            HandlingEvent event4 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-03-06"), trackingId2,
                    SampleVoyages.HONGKONG_TO_NEW_YORK.getVoyageNumber(),
                    SampleLocations.NEWYORK.getUnLocode(),
                    HandlingEvent.Type.LOAD);
            entityManager.persist(event4);
        } catch (CannotCreateHandlingEventException e) {
            throw new RuntimeException(e);
        }

        HandlingHistory handlingHistory2
                = handlingEventRepository.lookupHandlingHistoryOfCargo(trackingId2);
        jkl567.deriveDeliveryProgress(handlingHistory2);

        entityManager.persist(jkl567);

        // Cargo definition DEF789. This one will remain unrouted.
        TrackingId trackingId3 = new TrackingId("DEF789");

        RouteSpecification routeSpecification3 = new RouteSpecification(
                SampleLocations.HONGKONG, SampleLocations.MELBOURNE,
                DateUtil.toDate("2015-11-18"));

        Cargo def789 = new Cargo(trackingId3, routeSpecification3);
        entityManager.persist(def789);

        // Cargo definition MNO456. This one will be claimed properly.
        TrackingId trackingId4 = new TrackingId("MNO456");
        RouteSpecification routeSpecification4 = new RouteSpecification(
                SampleLocations.NEWYORK, SampleLocations.DALLAS, DateUtil.toDate("2015-3-27"));

        Cargo mno456 = new Cargo(trackingId4, routeSpecification4);

        Itinerary itinerary4 = new Itinerary(
                Arrays.asList(
                        new Leg(SampleVoyages.NEW_YORK_TO_DALLAS,
                                SampleLocations.NEWYORK,
                                SampleLocations.DALLAS,
                                DateUtil.toDate("2015-10-24"),
                                DateUtil.toDate("2015-10-25"))
                ));

        mno456.assignToRoute(itinerary4);
        entityManager.persist(mno456);

        try {
            HandlingEvent event1 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-10-18"), trackingId4,
                    null, SampleLocations.NEWYORK.getUnLocode(), HandlingEvent.Type.RECEIVE);

            entityManager.persist(event1);

            HandlingEvent event2 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-10-24"), trackingId4,
                    SampleVoyages.NEW_YORK_TO_DALLAS.getVoyageNumber(),
                    SampleLocations.NEWYORK.getUnLocode(), HandlingEvent.Type.LOAD);

            entityManager.persist(event2);

            HandlingEvent event3 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-10-25"), trackingId4,
                    SampleVoyages.NEW_YORK_TO_DALLAS.getVoyageNumber(),
                    SampleLocations.DALLAS.getUnLocode(), HandlingEvent.Type.UNLOAD);

            entityManager.persist(event3);

            HandlingEvent event4 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-10-26"), trackingId4,
                    null, SampleLocations.DALLAS.getUnLocode(), HandlingEvent.Type.CUSTOMS);

            entityManager.persist(event4);

            HandlingEvent event5 = handlingEventFactory.createHandlingEvent(
                    new Date(), DateUtil.toDate("2015-10-27"), trackingId4,
                    null, SampleLocations.DALLAS.getUnLocode(), HandlingEvent.Type.CLAIM);

            entityManager.persist(event5);

            HandlingHistory handlingHistory3
                    = handlingEventRepository.lookupHandlingHistoryOfCargo(trackingId4);

            mno456.deriveDeliveryProgress(handlingHistory3);

            entityManager.persist(mno456);
        } catch (CannotCreateHandlingEventException e) {
            throw new RuntimeException(e);
        }
    }
}
