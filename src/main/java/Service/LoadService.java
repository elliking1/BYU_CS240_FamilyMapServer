package Service;

import DAO.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.LoadRequest;
import Result.StandardResult;

import java.sql.Connection;

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
            StandardResult result = clearService.clearDatabase();

            Connection conn = dbConnect.openConnection();
            UserDAO userDAO = new UserDAO(conn);
            PersonDAO personDAO = new PersonDAO(conn);
            EventDAO eventDAO = new EventDAO(conn);

            User[] users = request.getUsers();
            Person[] people = request.getPersons();
            Event[] events = request.getEvents();

            int numUsers = 0;
            int numPersons = 0;
            int numEvents = 0;
            for(int i = 0; i < users.length; i++) {
                userDAO.addUser(users[i]);
                numUsers++;
            }

            for(int i = 0; i < people.length; i++) {
                personDAO.addPerson(people[i]);
                numPersons++;
            }

            for(int i = 0; i < events.length; i++) {
                eventDAO.addEvent(events[i]);
                numEvents++;
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
