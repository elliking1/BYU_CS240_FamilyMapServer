package Service;

import DAO.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.LoadRequest;
import Result.StandardResult;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class clears all data from the database and then loads
 * the posted user, person, and event data into the database.
 * @author Cameron Brown
 */
public class LoadService {
    /** Empty Constructor for LoadService
     *
     * */
    public LoadService() {

    }

    /** Clears data and loads new data into database
     *
     * @return Returns message of the success or error of the Load Operation
     *
     * @param request Takes data to be loaded into database
     * */
    public StandardResult load(LoadRequest request) {
        DatabaseConnect dbConnect = new DatabaseConnect();
        try {
            ClearService clearService = new ClearService();
            clearService.clearDatabase();

            Connection conn = dbConnect.openConnection();
            UserDAO userDAO = new UserDAO(conn);
            PersonDAO personDAO = new PersonDAO(conn);
            EventDAO eventDAO = new EventDAO(conn);

            ArrayList<User> users = request.getUsers();
            ArrayList<Person> persons = request.getPersons();
            ArrayList<Event> events = request.getEvents();

            int numUsers = 0;
            int numPersons = 0;
            int numEvents = 0;
            try {
                for (User user : users) {
                    userDAO.addUser(user);
                    numUsers++;
                }

                for (Person person : persons) {
                    personDAO.addPerson(person);
                    numPersons++;
                }

                for (Event event : events) {
                    eventDAO.addEvent(event);
                    numEvents++;
                }
            } catch(DatabaseException b) {
                try {
                    dbConnect.closeConnection(false);
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }
                return new StandardResult("error - invalid request data");
            }

            dbConnect.closeConnection(true);
            return new StandardResult("Successfully added " + numUsers + " users, " + numPersons +
                                      " persons, and " + numEvents + " events to the database.");
        } catch (DatabaseException d) {
            try {
                dbConnect.closeConnection(false);
            } catch (DatabaseException b) {
                b.printStackTrace();
            }
            return new StandardResult("Internal server error");
        }
    }
}
