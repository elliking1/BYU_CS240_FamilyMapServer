package Result;

import Model.Person;

import java.util.ArrayList;

/**
 * This class handles the result of returning all family members of current user
 * @author Cameron Brown
 */
public class AllPeopleResult extends StandardResult {
    /** Array of Person Objects */
    private ArrayList<Person> data;

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
        this.data = persons;
    }

    /** Constructor for AllPeopleResult Failures
     *
     * @param message Error message
     * */
    public AllPeopleResult(String message) {
        this.message = message;
    }

    public ArrayList<Person> getData() {
        return data;
    }

    public void setData(ArrayList<Person> data) {
        this.data = data;
    }
}
