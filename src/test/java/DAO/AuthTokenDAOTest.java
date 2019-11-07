package DAO;

import Model.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthTokenDAOTest {
    private DatabaseConnect db;
    private AuthToken myAuthToken;

    @BeforeEach
    void setUp() throws Exception {
        //here we can set up any classes or variables we will need for the rest of our tests

        //lets create a new database
        db = new DatabaseConnect();

        //and a new User with random data
        myAuthToken = new AuthToken("1234567", "MyUserName");

        //and make sure to initialize our tables since we don't know if our database files exist yet
        db.openConnection();
        db.createTables();
        db.closeConnection(true);
    }

    @AfterEach
    void tearDown() throws Exception {
        //here we can get rid of anything from our tests we don't want to affect the rest of our program
        //lets clear the tables so that any data we entered for testing doesn't linger in our files
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }
    @Test
    void addPass() throws Exception {
        //We want to make sure add works
        //First lets create a User that we'll set to null. We'll use this to make sure what we put
        //in the database is actually there.
        AuthToken compareTest = null;

        try {
            //Let's get our connection and make a new DAO
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            AuthTokenDAO aDao = new AuthTokenDAO(conn);

            aDao.addToken(myAuthToken);
            //So lets use a query method to get the AuthToken that we just put in back out
            compareTest = aDao.queryToken(myAuthToken.getToken());
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
        assertEquals(myAuthToken, compareTest);

    }

    @Test
    void addFail() throws Exception {
        //lets do this test again but this time lets try to make it fail

        // NOTE: The correct way to test for an exception in Junit 5 is to use an assertThrows
        // with a lambda function. However, lambda functions are beyond the scope of this class
        // so we are doing it another way.
        boolean didItWork = true;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            AuthTokenDAO aDao = new AuthTokenDAO(conn);
            //if we call the method the first time it will add it successfully
            aDao.addToken(myAuthToken);
            //but our sql table is set up so that "UserName" must be unique. So trying to insert it
            //again will cause the method to throw an exception
            aDao.addToken(myAuthToken);
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
        //to make sure that our User is not in the database
        //Set our compareTest to an actual User
        AuthToken compareTest = myAuthToken;
        try {
            Connection conn = db.openConnection();
            AuthTokenDAO aDao = new AuthTokenDAO(conn);
            //and then get something back from our find. If the User is not in the database we
            //should have just changed our compareTest to a null object
            compareTest = aDao.queryToken(myAuthToken.getToken());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        //Now make sure that compareTest is indeed null
        assertNull(compareTest);
    }

    @Test
    void deleteTokenPass() throws Exception {
        AuthToken compareTest = null;

        try {
            //Let's get our connection and make a new DAO
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            AuthTokenDAO aDao = new AuthTokenDAO(conn);

            aDao.deleteToken(myAuthToken.getToken());

            compareTest = aDao.queryToken(myAuthToken.getToken());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        assertNull(compareTest);
    }

    @Test
    void deleteTokenFail() throws Exception {
        AuthToken compareTest = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            AuthTokenDAO aDao = new AuthTokenDAO(conn);
            aDao.addToken(myAuthToken);
            aDao.deleteToken("Token");
            compareTest = aDao.queryToken(myAuthToken.getToken());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNotNull(compareTest);
        assertEquals(myAuthToken, compareTest);

    }

    @Test
    void queryTokenPass() throws Exception {

        AuthToken compareTest = null;

        try {
            //Let's get our connection and make a new DAO
            Connection conn = db.openConnection();
            AuthTokenDAO aDAO = new AuthTokenDAO(conn);

            aDAO.addToken(myAuthToken);

            compareTest = aDAO.queryToken(myAuthToken.getToken());
            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }

        assertNotNull(compareTest);

        assertEquals(myAuthToken, compareTest);

    }

    @Test
    void queryTokenFail() throws Exception {
        AuthToken wasFound = null;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);
            AuthTokenDAO aDao = new AuthTokenDAO(conn);
            wasFound = aDao.queryToken(myAuthToken.getToken());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNull(wasFound);
    }

    @Test
    void clearAuthTokensTablePass() throws Exception {
        // Test if it cleared everything by adding two AuthTokens with no related information
        // to the database and make sure they were both deleted.
        AuthToken compareTest = null;
        AuthToken compareTestTwo = null;
        AuthToken AuthTokenTwo;
        try {
            Connection conn = db.openConnection();
            conn.setAutoCommit(false);

            AuthTokenDAO aDao = new AuthTokenDAO(conn);

            aDao.addToken(myAuthToken);

            AuthTokenTwo = new AuthToken("dfadsf44", "coolPerson");
            aDao.addToken(AuthTokenTwo);

            aDao.clearAuthTokensTable();

            compareTest = aDao.queryToken(myAuthToken.getToken());
            compareTestTwo = aDao.queryToken(AuthTokenTwo.getToken());

            db.closeConnection(true);
        } catch (DatabaseException e) {
            db.closeConnection(false);
        }
        assertNull(compareTest);
        assertNull(compareTestTwo);
    }

}
