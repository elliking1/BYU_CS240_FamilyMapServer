package Service;

import DAO.*;
import Model.User;
import Result.StandardResult;

import java.sql.Connection;

/**
 * This class fills the database
 * @author Cameron Brown
 */
public class FillService {
    /** Empty Constructor for FillService
     *
     * */
    public FillService() {

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
            Connection conn = dbConnect.openConnection();
            if (generations < 0) {
                return new StandardResult("error - Invalid generations parameter.");
            }

            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.queryUser(userName);

            if (user == null) {
                return new StandardResult("error - Invalid username.");
            }

            int numPersons = 0;
            int numEvents = 0;
            PersonDAO personDAO = new PersonDAO(conn);
            EventDAO eventDAO = new EventDAO(conn);

            personDAO.deleteRelativesOfPerson(userName);
            eventDAO.deleteAllEventsOfUser(userName);

            // TODO: Generate the data to fill the database




            dbConnect.closeConnection(true);
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
}
