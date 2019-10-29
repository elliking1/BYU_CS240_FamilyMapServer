package DAO;

import Model.Person;
import java.sql.*;
import java.util.ArrayList;

/**
 * This class accesses the Person table and its data in the database
 * @author Cameron Brown
 */
public class PersonDAO {
    /**
     * Database connection
     */
    private Connection myConnection;

    /** Empty constructor for PersonDAO
     *
     * */
    public PersonDAO() {

    }

    /** Constructor for PersonDAO with a connection parameter
     *
     * @param conn Database connection
     * */
    public PersonDAO(Connection conn) {
        this.myConnection = conn;
    }

    /** This method adds a new person to the database
     *
     * @param newPerson The person to be added
     *
     * @throws DatabaseException if an error occurs trying to access it
     * */
    public void addPerson(Person newPerson) throws DatabaseException {
        String sql = "INSERT INTO Persons(PersonID, AssociatedUserName, FirstName, LastName, Gender, FatherID, MotherID, SpouseID) VALUES(?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, newPerson.getPersonID());
            stmt.setString(2, newPerson.getAssociatedUserName());
            stmt.setString(3, newPerson.getFirstName());
            stmt.setString(4, newPerson.getLastName());
            stmt.setString(5, newPerson.getGender());
            stmt.setString(6, newPerson.getFatherID());
            stmt.setString(7, newPerson.getMotherID());
            stmt.setString(8, newPerson.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered while inserting Person data into the database");
        }
    }

    /** This method removes a person from the database
     *
     * @param personID The ID of the person to be deleted
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    public void deletePerson(String personID) throws DatabaseException {
        String sql = "DELETE FROM Persons WHERE PersonID = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, personID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while deleting person");
        }
    }

    /** This method removes all relatives of a single person from the database
     *
     * @param userName The User Name of all people to be deleted
     *
     * @throws DatabaseException Error when accessing database
     * */
    public void deleteRelativesOfPerson(String userName) throws DatabaseException {
        String sql = "DELETE FROM Persons WHERE AssociatedUserName = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while deleting person");
        }
    }

    /** This method tries to find a person in the database
     *
     * @param personID The ID of the person being queried
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     *
     * @return The person being queried
     * */
    public Person queryPerson(String personID) throws DatabaseException {
        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM Persons WHERE PersonID = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("PersonID"), rs.getString("AssociatedUserName"),
                        rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Gender"),
                        rs.getString("FatherID"), rs.getString("MotherID"), rs.getString("SpouseID"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while finding person");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /** This method tries to find all relatives of a specific user in the database
     *
     * @param userName The UserName of the user whose people are being queried for
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     *
     * @return A list of people objects
     * */
    public ArrayList<Person> queryAllRelativesOfUser(String userName) throws DatabaseException {
        ArrayList<Person> list = new ArrayList<Person>();

        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM Persons WHERE AssociatedUserName = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                person = new Person(rs.getString("PersonID"), rs.getString("AssociatedUsername"),
                        rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Gender"),
                        rs.getString("FatherID"), rs.getString("MotherID"), rs.getString("SpouseID"));
                list.add(person);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while finding person");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    /** This method clears the entire Person table
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    public void clearPersonsTable() throws DatabaseException {
        try (Statement stmt = myConnection.createStatement()){
            String sql = "DELETE FROM Persons;";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DatabaseException("SQL Error encountered while clearing tables");
        }
    }
}
