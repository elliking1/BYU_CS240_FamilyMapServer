package Result;

import Model.Event;

import java.util.ArrayList;

/**
 * This class handles the result of returning all events for all family members of current user
 * @author Cameron Brown
 */
public class AllEventsResult extends StandardResult {
    /** Array of Event Objects */
    private ArrayList<Event> data;


    /** Empty constructor for AllEventsResult
     *
     * */
    public AllEventsResult() {

    }

    /** Constructor for AllEventsResult successes
     *
     * @param events List of events that need to be returned
     * */
    public AllEventsResult(ArrayList<Event> events) {
        this.data = events;
    }

    /** Constructor for AllEventsResult failures
     *
     * @param message The error message
     * */
    public AllEventsResult(String message) {
        this.message = message;
    }

    public ArrayList<Event> getData() {
        return data;
    }

    public void setData(ArrayList<Event> data) {
        this.data = data;
    }

}
