package Service;

import DAO.DatabaseConnect;
import DAO.DatabaseException;
import Result.StandardResult;

import java.sql.Connection;

/**
 * This class deletes ALL data from the database.
 * @author Cameron Brown
 */
public class ClearService {

    /** Empty constructor of ClearService
     *
     * */
    public ClearService() {

    }

    /** Clears database and returns ClearResult
     *
     * @return Returns the success or failure of the clear operation
     * */
    public StandardResult clearDatabase() {
        try {
            DatabaseConnect dbConnect = new DatabaseConnect();
            dbConnect.openConnection();
            dbConnect.clearTables();
            dbConnect.closeConnection(true);
            return new StandardResult("Clear succeeded.");
        } catch (DatabaseException d) {
            return new StandardResult("Internal server error");
        }
    }


}
