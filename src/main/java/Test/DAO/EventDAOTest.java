package Test.DAO;

import DAO.Database;
import DAO.DatabaseException;
import DAO.EventDAO;
import Model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EventDAOTest {
    private Database db;
    private Event myEvent;

    @BeforeEach
    public void setUp() throws Exception {
        //here we can set up any classes or variables we will need for the rest of our tests
        //lets create a new database
        db = new Database();
        //and a new event with random data
        myEvent = new Event("Biking_123A", "Gale", "Gale123A",
                10.3f, 10.3f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        //and make sure to initialize our tables since we don't know if our database files exist yet
        db.openConnection();
        db.createTables();
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws Exception {
        //here we can get rid of anything from our tests we don't want to affect the rest of our program
        //lets clear the tables so that any data we entered for testing doesn't linger in our files
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void addEventPass() throws Exception {
        //We want to make sure insert works
        //First lets create an Event that we'll set to null. We'll use this to make sure what we put
        //in the database is actually there.
        Event compareTest = null;

        try {
            //Let's get our connection and make a new DAO
            Connection conn = db.openConnection();
            EventDAO eDao = new EventDAO(conn);
            //While insert returns a bool we can't use that to verify that our function actually worked
            //only that it ran without causing an error
            eDao.addEvent(myEvent);
            //So lets use a find method to get the event that we just put in back out
            compareTest = eDao.queryEvent(myEvent.getEventID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(myEvent, compareTest);

    }

    @Test
    public void addEventFail() throws Exception {
        //lets do this test again but this time lets try to make it fail

        // NOTE: The correct way to test for an exception in Junit 5 is to use an assertThrows
        // with a lambda function. However, lambda functions are beyond the scope of this class
        // so we are doing it another way.
        boolean didItWork = true;
        try {
            Connection conn = db.openConnection();
            EventDAO eDao = new EventDAO(conn);
            //if we call the method the first time it will insert it successfully
            eDao.addEvent(myEvent);
            //but our sql table is set up so that "eventID" must be unique. So trying to insert it
            //again will cause the method to throw an exception
            eDao.addEvent(myEvent);
            db.closeConnection(true);
        } catch (DatabaseException e) {
            //If we catch an exception we will end up in here, where we can change our boolean to
            //false to show that our function failed to perform correctly
            db.closeConnection(false);
            didItWork = false;
        }
        //Check to make sure that we did in fact enter our catch statement
        assertFalse(didItWork);

        //Since we know our database encountered an error, both instances of insert should have been
        //rolled back. So for added security lets make one more quick check using our find function
        //to make sure that our event is not in the database
        //Set our compareTest to an actual event
        Event compareTest = myEvent;
        try {
            Connection conn = db.openConnection();
            EventDAO eDao = new EventDAO(conn);
            //and then get something back from our find. If the event is not in the database we
            //should have just changed our compareTest to a null object
            compareTest = eDao.queryEvent(myEvent.getEventID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        //Now make sure that compareTest is indeed null
        assertNull(compareTest);
    }

    @Test
    public void deleteEventPass() throws Exception {

        Event compareTest = null;

        try {
            Connection conn = db.openConnection();
            EventDAO eDao = new EventDAO(conn);
            eDao.addEvent(myEvent);

            eDao.deleteEvent(myEvent.getEventID());

            compareTest = eDao.queryEvent(myEvent.getEventID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        assertNull(compareTest);
    }

    @Test
    public void deleteEventFail() throws Exception {

        Event compareTest = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            EventDAO eDao = new EventDAO(conn);
            eDao.addEvent(myEvent);
            eDao.deleteEvent("Event");
            compareTest = eDao.queryEvent(myEvent.getEventID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotNull(compareTest);
        assertEquals(myEvent, compareTest);

    }

    @Test
    public void deleteAllEventsOfUserPass() throws Exception {
        Event compareTest = null;
        Event compareTestTwo = null;
        Event EventTwo = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            EventDAO eDao = new EventDAO(conn);
            eDao.addEvent(myEvent);
            Event eventTwo = new Event("Event2", "Gale", "person1",
                    64.1f, -21.8f, "Iceland", "Reykjavik", "Birth", 2001);

            eDao.addEvent(eventTwo);
            eDao.deleteAllEventsOfUser(myEvent.getAssociatedUserName());

            compareTest = eDao.queryEvent(myEvent.getEventID());
            compareTestTwo = eDao.queryEvent(eventTwo.getEventID());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNull(compareTest);
        assertNull(compareTestTwo);
    }

    @Test
    public void deleteAllEventsOfUserFail() throws Exception {
        Event compareTest = null;
        Event compareTestTwo = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            EventDAO eDao = new EventDAO(conn);
            eDao.addEvent(myEvent);
            Event eventTwo = new Event("Event2", "Gale", "person1",
                    64.1f, -21.8f, "Iceland", "Reykjavik", "Birth", 2001);
            eDao.addEvent(eventTwo);
            eDao.deleteAllEventsOfUser("14324132");

            compareTest = eDao.queryEvent(myEvent.getEventID());
            compareTestTwo = eDao.queryEvent(eventTwo.getEventID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotNull(compareTest);
        assertNotNull(compareTestTwo);
    }

    @Test
    public void queryEventPass() throws Exception {

        Event compareTest = null;

        try {
            //Let's get our connection and make a new DAO
            Connection conn = db.openConnection();
            EventDAO eDao = new EventDAO(conn);

            eDao.addEvent(myEvent);

            compareTest = eDao.queryEvent(myEvent.getEventID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        assertNotNull(compareTest);

        assertEquals(myEvent, compareTest);

    }

    @Test
    public void queryEventFail() throws Exception {
        Event wasFound = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            EventDAO eDao = new EventDAO(conn);
            wasFound = eDao.queryEvent(myEvent.getEventID());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNull(wasFound);
    }

    @Test
    public void queryAllRelativesOfUserPass() throws Exception {
        ArrayList<Event> peopleList = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            EventDAO eDao = new EventDAO(conn);
            eDao.addEvent(myEvent);
            Event eventTwo = new Event("Event2", "Gale", "person1",
                    64.1f, -21.8f, "Iceland", "Reykjavik", "Birth", 2001);
            eDao.addEvent(eventTwo);
            peopleList = eDao.queryAllEventsOfUser(myEvent.getAssociatedUserName());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotNull(peopleList);
        assertEquals(peopleList.size(), 2);
    }

    @Test
    public void queryAllRelativesOfUserFail() throws Exception {
        ArrayList<Event> peopleList = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            EventDAO eDao = new EventDAO(conn);
            eDao.addEvent(myEvent);
            Event eventTwo = new Event("Event2", "Gale", "person1",
                    64.1f, -21.8f, "Iceland", "Reykjavik", "Birth", 2001);
            eDao.addEvent(eventTwo);
            peopleList = eDao.queryAllEventsOfUser("dafdfds");

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotEquals(peopleList.size(), 2);
    }

    @Test
    public void clearEventsTablePass() throws Exception {
        Event compareTest = null;
        Event compareTestTwo = null;
        Event EventTwo = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            EventDAO eDao = new EventDAO(conn);
            eDao.addEvent(myEvent);
            Event eventTwo = new Event("Event2", "Gale", "person1",
                    64.1f, -21.8f, "Iceland", "Reykjavik", "Birth", 2001);
            eDao.addEvent(eventTwo);

            eDao.clearEventsTable();

            compareTest = eDao.queryEvent(myEvent.getEventID());
            compareTestTwo = eDao.queryEvent(eventTwo.getEventID());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNull(compareTest);
        assertNull(compareTestTwo);
    }
}

