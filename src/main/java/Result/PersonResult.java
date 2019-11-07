package Result;

import java.util.Objects;

/**
 * This class handles results for returning a single person object with specified ID
 * @author Cameron Brown
 *
 */
public class PersonResult extends StandardResult {
    /** UserName of person */
    private String associatedUsername;

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

    /** SpouseID, Could be Null */
    private String spouseID;

    /** Empty constructor for PersonResult
     *
     * */
    public PersonResult() {

    }
    /** Constructor for PersonResult Successes
     *
     * @param associatedUserName UserName of Person that needs to be returned
     * @param personID PersonID of Person that needs to be returned
     * @param firstName First name of Person that needs to be returned
     * @param lastName Last name of Person that needs to be returned
     * @param gender Gender of of Person that needs to be returned
     * @param fatherID PersonID of this person's father
     * @param motherID PersonID of this person's mother
     * @param spouseID PersonID of this person's spouse
     * */
    public PersonResult(String associatedUserName, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.associatedUsername = associatedUserName;
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
     * @param message Error message
     * */
    public PersonResult(String message) {
        this.message = message;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonResult that = (PersonResult) o;
        return associatedUsername.equals(that.associatedUsername) &&
                personID.equals(that.personID) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                gender.equals(that.gender) &&
                Objects.equals(fatherID, that.fatherID) &&
                Objects.equals(motherID, that.motherID) &&
                Objects.equals(spouseID, that.spouseID);
    }

}
