package Result;

import Model.Person;

import java.util.ArrayList;

/**
 * This class handles the result of returning all family members of current user
 * @author Cameron Brown
 */
public class AllPeopleResult extends StandardResult {
    /** Array of Person Objects */
    private ArrayList<Person> persons;

    /** Empty constructor for AllPeopleResult
     *
     * */
    public AllPeopleResult() {

    }

    /** Constructor for AllPeopleResult Successes
     *
     * @param persons List of people to return
     * */
    public AllPeopleResult(ArrayList<Person> persons) {
        this.persons = persons;
    }

    /** Constructor for AllPeopleResult Failures
     *
     * @param message Error message
     * */
    public AllPeopleResult(String message) {
        this.message = message;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
}
