package Model;
/**
 * This class represents a big life event for a person
 * @author Cameron Brown
 */
public class Event {
    /** EventID */
    private String eventID;

    /** UserName of person that this event belongs to */
    private String associatedUsername;

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

    /** Empty constructor for Event
     *
     * */
    public Event() {

    }

    /** Non-empty constructor for Event
     *
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
    public Event(String eventID, String associatedUsername, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Event) {
            Event oEvent = (Event) o;
            return oEvent.getEventID().equals(getEventID()) &&
                    oEvent.getAssociatedUsername().equals(getAssociatedUsername()) &&
                    oEvent.getPersonID().equals(getPersonID()) &&
                    oEvent.getLatitude() == (getLatitude()) &&
                    oEvent.getLongitude() == (getLongitude()) &&
                    oEvent.getCountry().equals(getCountry()) &&
                    oEvent.getCity().equals(getCity()) &&
                    oEvent.getEventType().equals(getEventType()) &&
                    oEvent.getYear() == (getYear());
        } else {
            return false;
        }
    }
}
