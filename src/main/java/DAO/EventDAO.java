package DAO;

import Model.Event;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class accesses the Event table and its data in the database
 * @author Cameron Brown
 */
public class EventDAO {
    /**
     * Database connection
     */
    private Connection myConnection;

    /** Empty constructor for EventDAO
     *
     * */
    public EventDAO() {

    }

    /** Constructor for PersonDAO with a connection parameter
     *
     * @param conn Database connection
     * */
    public EventDAO(Connection conn) {
        this.myConnection = conn;
    }

    /** This method adds a new event to the database
     *
     * @param newEvent The event to be added
     *
     * @throws DatabaseException Error with accessing database
     * */
    public void addEvent(Event newEvent) throws DatabaseException {
        String sql = "INSERT INTO Events (EventID, AssociatedUserName, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, newEvent.getEventID());
            stmt.setString(2, newEvent.getAssociatedUsername());
            stmt.setString(3, newEvent.getPersonID());
            stmt.setDouble(4, newEvent.getLatitude());
            stmt.setDouble(5, newEvent.getLongitude());
            stmt.setString(6, newEvent.getCountry());
            stmt.setString(7, newEvent.getCity());
            stmt.setString(8, newEvent.getEventType());
            stmt.setInt(9, newEvent.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered while inserting into the database");
        }
    }

    /** This method removes an event from the database
     *
     * @param eventID The ID of the event to be removed
     *
     * @throws DatabaseException An error that occurs while trying to access the database
     * */
    public void deleteEvent(String eventID) throws DatabaseException {
        String sql = "DELETE FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1,eventID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while deleting user");
        }
    }

    /** This method deletes all events that match a certain username from the database
     *
     * @param userName The User Name of all events to be deleted
     *
     * @throws DatabaseException An error that occurs while trying to access Database
     * */
    public void deleteAllEventsOfUser(String userName) throws DatabaseException {
        String sql = "DELETE FROM Events WHERE AssociatedUserName = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while deleting user");
        }
    }

    /** This method tries to find an event in the database
     *
     * @param eventID The ID of the event queried for
     *
     * @throws DatabaseException Error that occurs while accessing database
     *
     * @return Event model
     * */
    public Event queryEvent(String eventID) throws DatabaseException {
        Event event;
        ResultSet rs = null;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUserName"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while finding event");
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

    /** This method tries to find all events of all family members of a specific user
     *
     * @param userName The UserName of the user whose events are being queried for
     *
     * @throws DatabaseException An exception that occurs while accessing the database
     *
     * @return All events of all family members for a specific user
     * */
    public ArrayList<Event> queryAllEventsOfUser(String userName) throws DatabaseException {
        Event event;
        ArrayList<Event> events = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM Events WHERE AssociatedUserName = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUserName"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while finding person");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** This method clears the entire Events table
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    public void clearEventsTable() throws DatabaseException {
        try (Statement stmt = myConnection.createStatement()){
            String sql = "DELETE FROM Events;";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DatabaseException("SQL Error encountered while clearing tables");
        }
    }
}
