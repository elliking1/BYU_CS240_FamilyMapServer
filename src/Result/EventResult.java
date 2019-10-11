package Result;

import Model.Event;

/**
 * This class handles results of returning all events for all family members of current user
 * @author Cameron Brown
 */
public class EventResult {
    /** Array of Event Objects */
    private Event[] events;

    /** Error message */
    private String message;

    /** Empty constructor for EventResult
     *
     * */
    public EventResult() {

    }

    /** Constructor for EventResult successes
     *
     * */
    public EventResult(Event[] events) {
        this.events = events;
    }

    /** Constructor for EventResult failures
     *
     * */
    public EventResult(String message) {
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
