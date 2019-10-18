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
     *
     * @return Connection to the database
     */
    public Connection openConnection() throws DatabaseException {
        try {
            final String CONNECTION_URL = "jdbd:sqlite:src/db.sqlite";

            myConnection = DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e){
            e.printStackTrace();
            throw new DatabaseException("Unable to open connection to database");
        }

        return myConnection;
    }

    /**
     * This method returns the connection to the database
     *
     * @throws DatabaseException  An exception that occurs while trying to access the connection
     *
     * @return The connection to the database
     */
    public Connection getConnection() throws DatabaseException {
        if(myConnection == null) {
            return openConnection();
        } else {
            return myConnection;
        }
    }


    /**
     * This method tries to close the database connection
     *
     * @param commit  A boolean value that specifies whether or not to commit the transactions before closing the database.
     * @throws DatabaseException  An exception that occurs while closing the connection to the database
     */
    public void closeConnection(boolean commit) throws DatabaseException {
        try {
            if (commit) {
                //This will commit the changes to the database
                myConnection.commit();
            } else {
                //If we find out something went wrong, pass a false into closeConnection and this
                //will rollback any changes we made during this connection
                myConnection.rollback();
            }

            myConnection.close();
            myConnection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Unable to close database connection");
        }
    }

    /**
     * This method creates the tables in the database
     *
     * @throws DatabaseException  An exception that occurs while trying to create the tables in the database
     */
    public void createTables() throws DatabaseException {

        try (Statement stmt = myConnection.createStatement()){
            //First lets open our connection to our database.

            //We pull out a statement from the connection we just established
            //Statements are the basis for our transactions in SQL
            //Format this string to be exactly like a sql create table command
            String sql = "CREATE TABLE IF NOT EXISTS Events " +
                    "(" +
                    "EventID text not null unique, " +
                    "AssociatedUsername text not null, " +
                    "PersonID text not null, " +
                    "Latitude float not null, " +
                    "Longitude float not null, " +
                    "Country text not null, " +
                    "City text not null, " +
                    "EventType text not null, " +
                    "Year int not null, " +
                    "primary key (EventID), " +
                    "foreign key (AssociatedUsername) references Users(Username), " +
                    "foreign key (PersonID) references Persons(PersonID)" +
                    ")";

            stmt.executeUpdate(sql);
            //if we got here without any problems we successfully created the table and can commit
        } catch (SQLException e) {
            //if our table creation caused an error, we can just not commit the changes that did happen
            throw new DatabaseException("SQL Error encountered while creating tables");
        }


    }

    /**
     * This method deletes the tables in the database
     *
     * @throws DatabaseException  An exception that occurs while trying to clear the tables in the database
     */
    public void clearTables() throws DatabaseException {

        try (Statement stmt = myConnection.createStatement()){
            String sql = "DELETE FROM Events";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DatabaseException("SQL Error encountered while clearing tables");
        }
    }
}
