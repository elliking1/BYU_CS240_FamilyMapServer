package Result;
/**
 * This class handles results for returning a single event with specified ID.
 * @author Cameron Brown
 */
public class EventResult extends StandardResult {
    /** UserName of person that this event belongs to */
    private String associatedUsername;

    /** EventID */
    private String eventID;

    /** PersonID of person this event belongs to */
    private String personID;

    /** Latitude of the event’s location */
    private float latitude;

    /** Longitude of the event’s location */
    private float longitude;

    /** Name of country where event occurred */
    private String country;

    /** Name of city where event occurred */
    private String city;

    /** Type of event */
    private String eventType;

    /** Year the event occurred */
    private int year;

    /** Empty constructor for EventResult
     *
     * */
    public EventResult() {

    }

    /** Constructor for EventResult successes
     * @param eventID Unique identifier of the event
     * @param associatedUsername UserName of user that the event belongs to
     * @param personID PersonID of User event belongs to
     * @param latitude Latitude of where the event was
     * @param longitude Longitude of where the event was
     * @param city City where event occurred
     * @param country Country where event occurred
     * @param eventType The type of event (i.e. birth, death, baptism, wedding, etc.)
     * @param year Year event occurred
     * */
    public EventResult(String associatedUsername, String eventID, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
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

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
