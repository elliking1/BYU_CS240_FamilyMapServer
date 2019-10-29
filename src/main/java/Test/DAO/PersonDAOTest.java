package Test.DAO;

import DAO.Database;
import DAO.DatabaseException;
import DAO.PersonDAO;
import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private Person myPerson;

    @BeforeEach
    public void setUp() throws Exception {
        //here we can set up any classes or variables we will need for the rest of our tests

        //lets create a new database
        db = new Database();

        //and a new person with random data
        myPerson = new Person("person1", "MyUserName", "Bob",
                              "Barker", "m", "person2", "person3", "person4");

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
    public void addPersonPass() throws Exception {
        //We want to make sure add works
        //First lets create a Person that we'll set to null. We'll use this to make sure what we put
        //in the database is actually there.
        Person compareTest = null;

        try {
            //Let's get our connection and make a new DAO
            Connection conn = db.openConnection();
            PersonDAO pDao = new PersonDAO(conn);

            pDao.addPerson(myPerson);
            //Lets use a query method to get the person that we just put in back out
            compareTest = pDao.queryPerson(myPerson.getPersonID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our add did put something in, and that it didn't change the
        //data in any way
        assertEquals(myPerson, compareTest);

    }

    @Test
    public void addPersonFail() throws Exception {

        boolean didItWork = true;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            PersonDAO pDao = new PersonDAO(conn);
            //if we call the method the first time it will add it successfully
            pDao.addPerson(myPerson);
            //but our sql table is set up so that "personID" must be unique. So trying to insert it
            //again will cause the method to throw an exception
            pDao.addPerson(myPerson);
            db.closeConnection(true);
        } catch (DatabaseException e) {
            //If we catch an exception we will end up in here, where we can change our boolean to
            //false to show that our function failed to perform correctly
            db.closeConnection(false);
            didItWork = false;
        }
        //Check to make sure that we did in fact enter our catch statement
        assertFalse(didItWork);

        //Since we know our database encountered an error, both instances of add should have been
        //rolled back. So for added security lets make one more quick check using our find function
        //to make sure that our person is not in the database
        //Set our compareTest to an actual person
        Person compareTest = myPerson;
        try {
            Connection conn = db.openConnection();
            PersonDAO pDao = new PersonDAO(conn);
            //and then get something back from our find. If the person is not in the database we
            //should have just changed our compareTest to a null object
            compareTest = pDao.queryPerson(myPerson.getPersonID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        //Now make sure that compareTest is indeed null
        assertNull(compareTest);
    }

    @Test
    public void deletePersonPass() throws Exception {

        Person compareTest = null;

        try {
            Connection conn = db.openConnection();
            PersonDAO pDao = new PersonDAO(conn);
            pDao.addPerson(myPerson);

            pDao.deletePerson(myPerson.getPersonID());

            compareTest = pDao.queryPerson(myPerson.getPersonID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        assertNull(compareTest);
    }

    @Test
    public void deletePersonFail() throws Exception {

        Person compareTest = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            PersonDAO pDao = new PersonDAO(conn);
            pDao.addPerson(myPerson);
            pDao.deletePerson("person");
            compareTest = pDao.queryPerson(myPerson.getPersonID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotNull(compareTest);
        assertEquals(myPerson, compareTest);

    }

    @Test
    public void deleteRelativesOfPersonPass() throws Exception {
        Person compareTest = null;
        Person compareTestTwo = null;
        Person personTwo = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            PersonDAO pDao = new PersonDAO(conn);
            pDao.addPerson(myPerson);
            personTwo = new Person("person2", "MyUserName", "Bob", "Barker", "m", "person0", "person5", "person3");
            pDao.addPerson(personTwo);
            pDao.deleteRelativesOfPerson(myPerson.getAssociatedUserName());

            compareTest = pDao.queryPerson(myPerson.getPersonID());
            compareTestTwo = pDao.queryPerson(personTwo.getPersonID());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNull(compareTest);
        assertNull(compareTestTwo);
    }

    @Test
    public void deleteRelativesOfPersonFail() throws Exception {
        Person compareTest = null;
        Person compareTestTwo = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            PersonDAO pDao = new PersonDAO(conn);
            pDao.addPerson(myPerson);
            Person personTwo = new Person("person2", "MyUserName", "Bob",
                    "Barker", "m", "person0", "person5", "person3");
            pDao.addPerson(personTwo);
            pDao.deleteRelativesOfPerson("14324132");

            compareTest = pDao.queryPerson(myPerson.getPersonID());
            compareTestTwo = pDao.queryPerson(personTwo.getPersonID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotNull(compareTest);
        assertNotNull(compareTestTwo);
    }

    @Test
    public void queryPersonPass() throws Exception {

        Person compareTest = null;

        try {
            //Let's get our connection and make a new DAO
            Connection conn = db.openConnection();
            PersonDAO pDao = new PersonDAO(conn);

            pDao.addPerson(myPerson);

            compareTest = pDao.queryPerson(myPerson.getPersonID());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        assertNotNull(compareTest);

        assertEquals(myPerson, compareTest);

    }

    @Test
    public void queryPersonFail() throws Exception {
        Person wasFound = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            PersonDAO pDao = new PersonDAO(conn);
            wasFound = pDao.queryPerson(myPerson.getPersonID());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNull(wasFound);
    }

    @Test
    public void queryAllRelativesOfUserPass() throws Exception {
        ArrayList<Person> peopleList = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            PersonDAO pDao = new PersonDAO(conn);
            pDao.addPerson(myPerson);
            Person personTwo = new Person("person2", "MyUserName", "Bob",
                    "Barker", "m", "person0", "person5", "person3");
            pDao.addPerson(personTwo);
            peopleList = pDao.queryAllRelativesOfUser(myPerson.getAssociatedUserName());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotNull(peopleList);
        assertEquals(peopleList.size(), 2);
    }

    @Test
    public void queryAllRelativesOfUserFail() throws Exception {
        ArrayList<Person> peopleList = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            PersonDAO pDao = new PersonDAO(conn);
            pDao.addPerson(myPerson);
            Person personTwo = new Person("person2", "MyUserName", "Bob",
                    "Barker", "m", "person0", "person5", "person3");
            pDao.addPerson(personTwo);
            peopleList = pDao.queryAllRelativesOfUser("dafdfds");

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotEquals(peopleList.size(), 2);
    }

    @Test
    public void clearPersonsTablePass() throws Exception {
        // Test if it cleared everything by adding two people with no related information
        // to the database and make sure they were both deleted.
        Person compareTest = null;
        Person compareTestTwo = null;
        Person personTwo = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);

            PersonDAO pDao = new PersonDAO(conn);

            pDao.addPerson(myPerson);

            personTwo = new Person("person2", "awesome2", "Stanley", "Kubrick", "m", "bobKubrick", "momKubrick", "divorced");
            pDao.addPerson(personTwo);

            pDao.clearPersonsTable();

            compareTest = pDao.queryPerson(myPerson.getPersonID());
            compareTestTwo = pDao.queryPerson(personTwo.getPersonID());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNull(compareTest);
        assertNull(compareTestTwo);
    }
}

