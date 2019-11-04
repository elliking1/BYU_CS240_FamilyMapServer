package Service;

import DAO.DatabaseConnect;
import DAO.DatabaseException;
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
        try {
            DatabaseConnect dbConnect = new DatabaseConnect();
            dbConnect.openConnection();
            dbConnect.clearTables();
            int numUsers = 0;
            int numPersons = 0;
            int numEvents = 0;

            // TODO: Add all the data to the database.

            dbConnect.closeConnection(true);
            return new StandardResult("Successfully added " + numUsers + " users, " + numPersons +
                                      " persons, and " + numEvents + " events to the database.");
        } catch (DatabaseException d) {
            return new StandardResult("Internal server error");
        }
    }
}
