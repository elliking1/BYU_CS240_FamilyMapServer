package Request;

import Model.Event;
import Model.Person;
import Model.User;

/**
 * This class handles requests for data to be loaded into the database
 * @author Cameron Brown
 */
public class LoadRequest {
    /** List of Users to be loaded into Database*/
    private User[] users;

    /** List of Persons to be loaded into Database */
    private Person[] persons;

    /** List of Events to be loaded into Database */
    private Event[] events;

    /** Empty constructur for LoadRequest
     *
     * */
    public LoadRequest() {

    }

    /** Non-empty constructur for LoadRequest
     *
     * */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
