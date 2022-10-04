package com.solvd.services.main;

import com.solvd.dao.impl.*;
import com.solvd.dao.interfaces.*;
import com.solvd.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrangePhotosessionService {

    private static final Logger LOGGER = LogManager.getLogger(ArrangePhotosessionService.class);

    private PhotostudioDAO photostudioDAO = new PhotostudioDAOImpl();
    private StudioDAO studioDAO = new StudioDAOImpl();
    private DressingRoomDAO dressingRoomDAO = new DressingRoomDAOImpl();
    private PhotographerDAO photographerDAO = new PhotographerDAOImpl();

    private VisagisteDAO visagisteDAO = new VisagisteDAOImpl();
    private HairdresserDAO hairdresserDAO = new HairdresserDAOImpl();
    private AbonementDAO abonementDAO = new AbonementDAOImpl();

    private LocationDAO locationDAO = new LocationDAOImpl();

    private CityDAO cityDAO = new CityDAOImpl();

    public LocalDateTime chooseDate() {
        boolean noValidDate = true;
        LocalDateTime dateTime = null;
        while (noValidDate) {
            try {
                LOGGER.info("Enter the date for photosession in format: yyyy-mm-dd");
                Scanner scan = new Scanner(System.in);
                String date = scan.nextLine();
                LOGGER.info("Enter the time for photosession in format: HH:mm");
                String time = scan.nextLine();
                String str = date + " " + time + ":00";

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                dateTime = LocalDateTime.parse(str, formatter);
                LocalDateTime now = LocalDateTime.now();
                if (dateTime.isBefore(now)) {
                    throw new DateTimeException("Wrong Day");
                }

                noValidDate = false;
                
            } catch (DateTimeException dateTimeException) {
                LOGGER.error("Wrong Format for Date / Time");
            }
        }
        return dateTime;
    }

    public int choosePhotostudio() {
        boolean noID = true;
        int id = 0;
        while (noID) {
            LOGGER.info("Available photostudios:");
            List<Photostudio> photostudios = photostudioDAO.getAll();
            for (Photostudio p : photostudios) {
                Location location = locationDAO.getById(p.getLocationId());
                City city = cityDAO.getById(location.getCityId());
                String loc = location.getAddress() + " " + city.getName();

                LOGGER.info("Photostudio id: {}, name {}, Location {}", p.getId(), p.getName(), loc);
            }
            LOGGER.info("Enter photostudio ID you want to get photosession");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();

            int finalId = id;
            Photostudio photostudio = photostudios.stream().filter(photostudio1 -> photostudio1.getId() == finalId).findAny().orElse(null);
            if (photostudio == null) {
                LOGGER.error("No photostudio with such id");
            } else {
                noID = false;
            }
        }
        return id;
    }

    public int chooseStudio(int photostudioId) {
        boolean noID = true;
        int id = 0;
        while (noID) {
            LOGGER.info("Available studios:");
            List<Studio> studios = studioDAO.getAll().stream().filter(studio -> studio.getPhotostudioId() == photostudioId).collect(Collectors.toList());
            LOGGER.info(studios);
            LOGGER.info("Enter photostudio ID you want to get photosession");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();

            int finalId = id;
            Studio studio = studios.stream().filter(studio1 -> studio1.getId() == finalId).findAny().orElse(null);
            if (studio == null) {
                LOGGER.error("No studio with such id");
            } else {
                noID = false;
            }
        }
        return id;
    }

    public int chooseDressingRoom(int photostudioId) {
        boolean noID = true;
        int id = 0;
        while (noID) {
            LOGGER.info("Available dressing room:");
            List<DressingRoom> dressingRooms = dressingRoomDAO.getAll().stream().filter(dressingRoom -> dressingRoom.getPhotostudioId() == photostudioId).collect(Collectors.toList());
            LOGGER.info(dressingRooms);
            LOGGER.info("Enter dressing room ID you want");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();

            int finalId = id;
            DressingRoom dressingRoom = dressingRooms.stream().filter(dressingRoom1 -> dressingRoom1.getId() == finalId).findAny().orElse(null);
            if (dressingRoom == null) {
                LOGGER.error("No dressing room  with such id");
            } else {
                noID = false;
            }
        }
        return id;
    }

    public int choosePhotographer() {
        boolean noID = true;
        int id = 0;
        while (noID) {
            LOGGER.info("Available photographers:");
            List<Photographer> photographers = photographerDAO.getAll();
            LOGGER.info(photographers);
            LOGGER.info("Enter photographer ID you want");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();

            int finalId = id;
            Photographer photographer = photographers.stream().filter(photographer1 -> photographer1.getId() == finalId).findAny().orElse(null);
            if (photographer == null) {
                LOGGER.error("No photographer with such id");
            } else {
                noID = false;
            }
        }
        return id;
    }

    public int chooseVisagiste() {
        boolean noID = true;
        int id = 0;
        while (noID) {
            LOGGER.info("Available visagistes:");
            List<Visagiste> visagistes = visagisteDAO.getAll();
            LOGGER.info(visagistes);
            LOGGER.info("Enter visagiste ID you want");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();

            int finalId = id;
            Visagiste visagiste = visagistes.stream().filter(visagiste1 -> visagiste1.getId() == finalId).findAny().orElse(null);
            if (visagiste == null) {
                LOGGER.error("No visagiste with such id");
            } else {
                noID = false;
            }
        }
        return id;
    }

    public int chooseHairDresser() {
        boolean noID = true;
        int id = 0;
        while (noID) {
            LOGGER.info("Available hairdressers:");
            List<Hairdresser> hairdressers = hairdresserDAO.getAll();
            LOGGER.info(hairdressers);
            LOGGER.info("Enter hairdresser ID you want");
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();

            int finalId = id;
            Hairdresser hairdresser = hairdressers.stream().filter(hairdresser1 -> hairdresser1.getId() == finalId).findAny().orElse(null);
            if (hairdresser == null) {
                LOGGER.error("No hairdresser with such id");
            } else {
                noID = false;
            }
        }
        return id;
    }

    public int applyAbonement(int clientId) {
        List<Abonement> abonements = abonementDAO.getAll();
        List<Abonement> abonements1 = abonements.stream().filter(abonement1 -> abonement1.getClientId() == clientId).collect(Collectors.toList());
        if (abonements1.isEmpty()) {
            LOGGER.info("No available abonements to apply");
            return 0;
        } else {
            LOGGER.info("Available clients abonements");
            LOGGER.info(abonements1);
            Scanner scanner = new Scanner(System.in);
            LOGGER.info("Type abonement id you want to apply");
            int abonementId = scanner.nextInt();
            Abonement abonement = abonements1.stream().filter(abonement1 -> abonement1.getId() == abonementId).findAny().orElse(null);
            if (abonement != null) {
                LOGGER.info("abonement will be applied");
                return abonementId;
            } else {
                return 0;
            }
        }
    }

    public double getTotalPrice(int studioId, int dressingRoomId,
                                int photographerId, int visagisteId, int hairdresserId) {
        double totalPrice = studioDAO.getById(studioId).getPrice() +
                dressingRoomDAO.getById(dressingRoomId).getPrice() +
                photographerDAO.getById(photographerId).getPrice() +
                visagisteDAO.getById(visagisteId).getPrice() +
                hairdresserDAO.getById(hairdresserId).getPrice();
        return totalPrice;
    }

    public void proceedPayment(double totalPrice, int clientId) {
        boolean unsuccessfulPayment = true;
        int abonementId = applyAbonement(clientId);
        double price = totalPrice;
        double abonementDiscount = 0;
        if (abonementId != 0) {
            Abonement abonement = abonementDAO.getById(abonementId);
            abonementDiscount = abonement.getPrice();
            price = totalPrice - abonementDiscount;
        }

        LOGGER.info("Price іs: " + totalPrice + " " + "discount is - " + abonementDiscount + " need to pay " + price);
        LOGGER.info("Payment by card - OK");
    }
}
