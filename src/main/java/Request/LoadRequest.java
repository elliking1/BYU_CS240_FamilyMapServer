package Request;

import Model.Event;
import Model.Person;
import Model.User;

import java.util.ArrayList;

/**
 * This class handles requests for data to be loaded into the database
 * @author Cameron Brown
 */
public class LoadRequest {
    /** List of Users to be loaded into Database*/
    private ArrayList<User> users;

    /** List of Persons to be loaded into Database */
    private ArrayList<Person> persons;

    /** List of Events to be loaded into Database */
    private ArrayList<Event> events;

    /** Empty constructor for LoadRequest
     *
     * */
    public LoadRequest() {

    }

    /** Non-empty constructor for LoadRequest
     *
     * @param users List of users to be loaded into database
     * @param persons List of people to be loaded into database
     * @param events List of events to be loaded into database
     * */
    public LoadRequest(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
