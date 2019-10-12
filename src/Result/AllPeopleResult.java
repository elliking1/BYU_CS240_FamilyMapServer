package Result;

import Model.Person;

/**
 * This class handles the result of returning all family members of current user
 * @author Cameron Brown
 */
public class AllPeopleResult {
    /** Array of Person Objects */
    private Person[] persons;

    /** Error message */
    private String message;

    /** Empty constructor for AllPeopleResult
     *
     * */
    public AllPeopleResult() {

    }

    /** Constructor for AllPeopleResult Successes
     *
     * */
    public AllPeopleResult(Person[] persons) {
        this.persons = persons;
    }

    /** Constructor for AllPeopleResult Failures
     *
     * */
    public AllPeopleResult(String message) {
        this.message = message;
    }
}
