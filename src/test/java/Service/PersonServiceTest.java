package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseConnect;
import DAO.DatabaseException;
import DAO.PersonDAO;
import Model.AuthToken;
import Model.Person;
import Result.PersonResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    private PersonService myService;
    private DatabaseConnect db;
    private Connection conn;
    private PersonDAO personDAO;
    private AuthTokenDAO authTokenDAO;

    @BeforeEach
    public void setUp() throws Exception {
        myService = new PersonService();
        /*
        Person testPerson1 = new Person("person1", "MyUserName", "Barney",
                "Barker", "m", "person2", "person3", "person4");
        Person testPerson2 = new Person("person2", "MyUserName", "Bob",
                "Barker", "m", "person10", "person11", "person3");
        Person testPerson3 = new Person("person3", "MyUserName", "Mom",
                "Barker", "f", "person12", "person13", "person2");
        Person testPerson4 = new Person("person4", "MyUserName", "Robin",
                "Barker", "f", "person6", "person7", "person1");

        AuthToken testToken1 = new AuthToken("token1", "MyUserName");
        AuthToken testToken2 = new AuthToken("token2", "MyUserName");
        AuthToken testToken3 = new AuthToken("token3", "MyUserName");
        AuthToken testToken4 = new AuthToken("token4", "NotMyUser");

        db = new DatabaseConnect();
        db.openConnection();

        personDAO = new PersonDAO(db.getConnection());
        personDAO.addPerson(testPerson1);
        personDAO.addPerson(testPerson2);
        personDAO.addPerson(testPerson3);
        personDAO.addPerson(testPerson4);

        authTokenDAO = new AuthTokenDAO(db.getConnection());
        authTokenDAO.addToken(testToken1);
        authTokenDAO.addToken(testToken2);
        authTokenDAO.addToken(testToken3);
        authTokenDAO.addToken(testToken4);

        db.closeConnection(true);
        */
    }

    @AfterEach
    public void tearDown() throws Exception {
        /*db.openConnection();
        db.clearTables();
        db.closeConnection(true);*/
    }

    @Test
    public void getPersonPass() throws Exception {
        PersonResult myResult = myService.getPerson("person2", "token3");
        PersonResult expected = new PersonResult("MyUserName", "person2",
                "Bob", "Barker", "m",
                "person10", "person11", "person3");
        assertEquals(myResult, expected);
    }

    @Test
    public void getPersonFail() throws Exception {
        PersonResult myResult = myService.getPerson("person4", "token4");
        PersonResult expected = new PersonResult("Error: Requested person does not belong to this user");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }


}
