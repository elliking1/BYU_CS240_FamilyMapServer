package Result;
/**
 * This class handles results for returning a single person object with specified ID
 * @author Cameron Brown
 */
public class PersonResult {
    /** UserName of person */
    private String associatedUserName;

    /** PersonID */
    private String personID;

    /** FirstName */
    private String firstName;

    /** LastName */
    private String lastName;

    /** Gender */
    private String gender;

    /** FatherID */
    private String fatherID;

    /** MotherID */
    private String motherID;

    /** SpouseID */
    private String spouseID;

    /** Error Message */
    private String message;

    /** Empty constructor for PersonResult
     *
     * */
    public PersonResult() {

    }
    /** Constructor for PersonResult Successes
     *
     *
     * */
    public PersonResult(String associatedUserName, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.associatedUserName = associatedUserName;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    /** Constructor for PersonResult Failures
     *
     *
     * */
    public PersonResult(String message) {
        this.message = message;
    }

    public String getAssociatedUserName() {
        return associatedUserName;
    }

    public void setAssociatedUserName(String associatedUserName) {
        this.associatedUserName = associatedUserName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
