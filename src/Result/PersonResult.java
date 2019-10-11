package Result;

import Model.Person;

/**
 * This class handles results of returning all family members of current user
 * @author Cameron Brown
 */
public class PersonResult {
    /** Array of Person Objects */
    private Person[] persons;

    /** Error message */
    private String message;

    /** Empty constructor for PersonResult
     *
     * */
    public PersonResult() {

    }

    /** Constructor for PersonResult Successes
     *
     * */
    public PersonResult(Person[] persons) {
        this.persons = persons;
    }

    /** Constructor for PersonResult Failures
     *
     * */
    public PersonResult(String message) {
        this.message = message;
    }
}
