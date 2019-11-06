package Service;

import DAO.*;
import Model.Event;
import Model.Person;
import Model.User;
import Result.StandardResult;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * This class fills the database
 * @author Cameron Brown
 */
public class FillService {

    private ArrayList<String> femaleFirstNames = new ArrayList<>();
    private ArrayList<String> maleFirstNames = new ArrayList<>();
    private ArrayList<String> lastNames = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();
    private Connection conn;

    /** Empty Constructor for FillService
     *
     * */
    public FillService() {
        createNameLists();
        createLocationList();
    }

    /** This fills database with the specified amount of generations for the specified user.
     *
     * @param userName The UserName of the person whose generation data will fill the database.
     * @param generations The number of generations of the user's ancestors to generate.
     *
     * @return Returns the success or failure of the fill operation
     * */
    public StandardResult fillDatabase(String userName, int generations) {
        DatabaseConnect dbConnect = new DatabaseConnect();
        try {
            conn = dbConnect.openConnection();
            if (generations < 0) {
                return new StandardResult("error - Invalid generations parameter.");
            }

            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.queryUser(userName);

            if (user == null) {
                return new StandardResult("error - Invalid username.");
            }


            PersonDAO personDAO = new PersonDAO(conn);
            EventDAO eventDAO = new EventDAO(conn);

            personDAO.deleteRelativesOfPerson(userName);
            eventDAO.deleteAllEventsOfUser(userName);
            personDAO.deletePerson(user.getPersonID());

            Person basePerson = new Person(user.getPersonID(), user.getUserName(), user.getFirstName(),
                                           user.getLastName(), user.getGender());

            Random rand = new Random();
            int birthYear = 1990;
            Location birthLocation = locations.get(rand.nextInt(locations.size()));
            String eventID = UUID.randomUUID().toString();
            Event birthEvent = new Event(eventID, user.getUserName(), basePerson.getPersonID(),
                                         birthLocation.getLatitude(), birthLocation.getLongitude(),
                                         birthLocation.getCountry(), birthLocation.getCity(), "Birth", birthYear);
            eventDAO.addEvent(birthEvent);


            generateAncestors(basePerson, generations, birthYear);

            dbConnect.closeConnection(true);
            int numPersons = 1;
            for(int i = 1; i < 5; i++){
                numPersons += (int) (Math.pow(2, i));
            }
            int numEvents = numPersons * 3 - 2;
            return new StandardResult("Successfully added " + numPersons + " persons and " + numEvents + " events to the database.");

        } catch (DatabaseException d) {
            try {
                dbConnect.closeConnection(false);
            } catch (DatabaseException b) {
                b.printStackTrace();
            }
            return new StandardResult("Internal server error");
        }
    }

    public void generateAncestors(Person person, int generations, int childBirthYear) throws DatabaseException {
        System.out.println("Generations = " + generations);
        String fatherName;
        String motherName;
        String lastName;
        Random rand;
        if(person.getGender().equals("m")) {
            lastName = person.getLastName();
        }
        else {
            rand = new Random();
            lastName = lastNames.get(rand.nextInt(lastNames.size()));
        }
        rand = new Random();
        fatherName = maleFirstNames.get(rand.nextInt(maleFirstNames.size()));

        rand = new Random();
        motherName = femaleFirstNames.get(rand.nextInt(femaleFirstNames.size()));

        Person father = new Person(person.getAssociatedUserName(), fatherName, lastName, "m");
        Person mother = new Person(person.getAssociatedUserName(), motherName, lastName, "f");
        mother.setSpouseID(father.getPersonID());
        father.setSpouseID(mother.getPersonID());
        PersonDAO personDAO = new PersonDAO(conn);

        person.setFatherID(father.getPersonID());
        person.setMotherID(mother.getPersonID());

        personDAO.addPerson(person);

        int birthYear = childBirthYear - 25;
        int deathYear = birthYear + 55;
        Location motherLocation = locations.get(rand.nextInt(locations.size()));
        Event motherBirthEvent = new Event(UUID.randomUUID().toString(), mother.getAssociatedUserName(), mother.getPersonID(),
                motherLocation.getLatitude(), motherLocation.getLongitude(),
                motherLocation.getCountry(), motherLocation.getCity(), "Birth", birthYear);

        Location fatherLocation = locations.get(rand.nextInt(locations.size()));
        Event fatherBirthEvent = new Event(UUID.randomUUID().toString(), father.getAssociatedUserName(), father.getPersonID(),
                fatherLocation.getLatitude(), fatherLocation.getLongitude(),
                fatherLocation.getCountry(), fatherLocation.getCity(), "Birth", birthYear);

        Event motherDeathEvent = new Event(UUID.randomUUID().toString(), mother.getAssociatedUserName(), mother.getPersonID(),
                motherLocation.getLatitude(), motherLocation.getLongitude(),
                motherLocation.getCountry(), motherLocation.getCity(), "Death", deathYear);

        Event fatherDeathEvent = new Event(UUID.randomUUID().toString(), father.getAssociatedUserName(), father.getPersonID(),
                fatherLocation.getLatitude(), fatherLocation.getLongitude(),
                fatherLocation.getCountry(), fatherLocation.getCity(), "Death", deathYear);

        EventDAO eventDAO = new EventDAO(conn);
        eventDAO.addEvent(motherBirthEvent);
        eventDAO.addEvent(fatherBirthEvent);
        eventDAO.addEvent(motherDeathEvent);
        eventDAO.addEvent(fatherDeathEvent);


        int marriageYear =  birthYear + 20;
        createMarriageEvent(father, mother, marriageYear);

        int adjustedGenerations = generations - 1;
        System.out.println("adjustedGenerations = " + adjustedGenerations);
        if(adjustedGenerations >= 1) {
            generateAncestors(mother, adjustedGenerations, birthYear);
            generateAncestors(father, adjustedGenerations, birthYear);
        }
    }

    public void createMarriageEvent(Person husband, Person wife, int year) throws DatabaseException {
        EventDAO eventDAO = new EventDAO(conn);
        String manMarriageID = UUID.randomUUID().toString();
        String femaleMarriageID = UUID.randomUUID().toString();
        Random rand = new Random();
        Location marriageLocation = locations.get(rand.nextInt(locations.size()));
        Event manEvent = new Event(manMarriageID, husband.getAssociatedUserName(), husband.getPersonID(),
                                   marriageLocation.getLatitude(), marriageLocation.getLongitude(),
                                   marriageLocation.getCountry(), marriageLocation.getCity(),
                                   "Marriage", year);
        Event femaleEvent = new Event(femaleMarriageID, wife.getAssociatedUserName(), wife.getPersonID(),
                marriageLocation.getLatitude(), marriageLocation.getLongitude(),
                marriageLocation.getCountry(), marriageLocation.getCity(),
                "Marriage", year);

        eventDAO.addEvent(manEvent);
        eventDAO.addEvent(femaleEvent);
    }

    public void createNameLists() {
        File maleNamesFile = new File("json/mnames.json");
        File femaleNamesFile = new File("json/fnames.json");
        File lastNamesFile = new File("json/snames.json");


        try {
            JsonReader reader = new JsonReader(new FileReader(maleNamesFile));
            Gson g = new Gson();
            Names maleNames = g.fromJson(reader, Names.class);

            reader = new JsonReader(new FileReader(femaleNamesFile));
            g = new Gson();
            Names femaleNames = g.fromJson(reader, Names.class);

            reader = new JsonReader(new FileReader(lastNamesFile));
            g = new Gson();
            Names lstNames = g.fromJson(reader, Names.class);

            lastNames = lstNames.getData();
            maleFirstNames = maleNames.getData();
            femaleFirstNames = femaleNames.getData();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createLocationList() {
        File locationsFile = new File("json/locations.json");

        try {
            JsonReader reader = new JsonReader(new FileReader(locationsFile));
            Gson g = new Gson();
            Locations places  = g.fromJson(reader, Locations.class);
            locations = places.getData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static class Names {
        private ArrayList<String> data;

        public Names(ArrayList<String> names) {
            this.data = names;
        }

        public ArrayList<String> getData() {
            return data;
        }

        public void setData(ArrayList<String> data) {
            this.data = data;
        }
    }

    public static class Locations {
        private ArrayList<Location> data;

        public Locations(ArrayList<Location> locations) {
            this.data = locations;
        }

        public ArrayList<Location> getData() {
            return data;
        }

        public void setData(ArrayList<Location> data) {
            this.data = data;
        }

    }

    public static class Location {
        private String country;
        private String city;
        private double latitude;
        private double longitude;

        public Location() {

        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

    }

}
