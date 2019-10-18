package DAO;

import Model.Event;

/**
 * This class accesses the Event table and its data in the database
 * @author Cameron Brown
 */
public class EventDAO {

    /** Empty constructor for EventDAO
     *
     * */
    public EventDAO() {

    }

    /** This method adds a new event to the database
     *
     * @param newEvent The event to be added
     * */
    public void addEvent(Event newEvent) {

    }

    /** This method removes an event from the database
     *
     * @param eventID The ID of the event to be removed
     * */
    public void deleteEvent(String eventID){

    }

    /** This method deletes all events that match a certain username from the database
     *
     * @param userName The User Name of all events to be deleted
     * */
    public void deleteUsersEvents(String userName) {

    }

    /** This method tries to find an event in the database
     *
     * @param eventID The ID of the event queried for
     *
     * @return Event model
     * */
    public Event query(String eventID) {

        return null;
    }

    /** This method tries to find all events in the database for a specific user
     *
     * @param userName The UserName of the user whose events are being queried for
     *
     * @return Event model
     * */
    public Event queryUsersEvents(String userName) {

        return null;
    }
}
