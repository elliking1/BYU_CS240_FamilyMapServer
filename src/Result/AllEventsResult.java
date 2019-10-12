package Result;

import Model.Event;

/**
 * This class handles the result of returning all events for all family members of current user
 * @author Cameron Brown
 */
public class AllEventsResult {
    /** Array of Event Objects */
    private Event[] events;

    /** Error message */
    private String message;

    /** Empty constructor for AllEventsResult
     *
     * */
    public AllEventsResult() {

    }

    /** Constructor for AllEventsResult successes
     *
     * */
    public AllEventsResult(Event[] events) {
        this.events = events;
    }

    /** Constructor for AllEventsResult failures
     *
     * */
    public AllEventsResult(String message) {
        this.message = message;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}