package DAO;

import java.sql.*;

/**
 * This class connects to the database
 * @author Cameron Brown
 */
public class Database {
    /**
     * Database connection
     */
    private Connection myConnection;

    /** Empty constructor for Database
     *
     * */
    public Database() {

    }

    /**
     * This method tries to open the database connection
     *
     * @throws DatabaseException  An exception that occurs while opening the connection to the database
     */
    public void openConnection() throws DatabaseException {

    }

    /**
     * This method tries to close the database connection
     *
     * @param commit  A boolean value that specifies whether or not to commit the transactions before closing the database.
     * @throws DatabaseException  An exception that occurs while closing the connection to the database
     */
    public void closeConnection(boolean commit) throws DatabaseException {

    }

}
