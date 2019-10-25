package Result;
/**
 * This class handles results for returning a single event with specified ID.
 * @author Cameron Brown
 */
public class EventResult {
    /** UserName of person that this event belongs to */
    private String associatedUserName;

    /** EventID */
    private String EventID;

    /** PersonID of person this event belongs to */
    private String personID;

    /** Latitude of the event’s location */
    private double latitude;

    /** Longitude of the event’s location */
    private double longitude;

    /** Name of country where event occurred */
    private String country;

    /** Name of city where event occurred */
    private String city;

    /** Type of event */
    private String eventType;

    /** Year the event occurred */
    private String year;

    /** Error message */
    private String message;

    /** Empty constructor for EventResult
     *
     * */
    public EventResult() {

    }

    /** Constructor for EventResult successes
     * @param eventID Unique identifier of the event
     * @param associatedUserName UserName of user that the event belongs to
     * @param personID PersonID of User event belongs to
     * @param latitude Latitude of where the event was
     * @param longitude Longitude of where the event was
     * @param city City where event occurred
     * @param country Country where event occurred
     * @param eventType The type of event (i.e. birth, death, baptism, wedding, etc.)
     * @param year Year event occurred
     * */
    public EventResult(String associatedUserName, String eventID, String personID, double latitude, double longitude, String country, String city, String eventType, String year) {
        this.associatedUserName = associatedUserName;
        EventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /** Constructor for EventResult failures
     *
     * @param message Error Message
     * */
    public EventResult(String message) {
        this.message = message;
    }

    public String getAssociatedUserName() {
        return associatedUserName;
    }

    public void setAssociatedUserName(String associatedUserName) {
        this.associatedUserName = associatedUserName;
    }

    public String getEventID() {
        return EventID;
    }

    public void setEventID(String eventID) {
        EventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}