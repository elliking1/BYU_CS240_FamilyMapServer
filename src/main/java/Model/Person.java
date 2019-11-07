package Model;

import java.util.UUID;

/**
 * This class represents a person
 * @author Cameron Brown
 */
public class Person {
    /** PersonID */
    private String personID;

    /** UserName of person */
    private String associatedUsername;

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

    /** Empty constructor for Person
     *
     * */
    public Person() {

    }

    /** Non-empty constructor for Person
     *
     * @param personID Unique identifier of person
     * @param associatedUsername UserName of person's FamilyMap account
     * @param firstName First name of person
     * @param lastName Last name of person
     * @param gender Gender of person
     * @param fatherID PersonID of this person's father
     * @param motherID PersonID of this person's mother
     * @param spouseID PersonID of this person's spouse
     * */
    public Person(String personID, String associatedUsername, String firstName, String lastName,
                  String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public Person(String userName, String firstName, String lastName, String gender) {
        this.personID = UUID.randomUUID().toString();
        this.associatedUsername = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.spouseID = null;
        this.motherID = null;
        this.fatherID = null;

    }

    public Person(String personID, String userName, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.spouseID = null;
        this.motherID = null;
        this.fatherID = null;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
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
        if (o == null)
            return false;
        if (o instanceof Person) {
            Person oPerson = (Person) o;
            return oPerson.getPersonID().equals(getPersonID()) &&
                    oPerson.getAssociatedUsername().equals(getAssociatedUsername()) &&
                    oPerson.getFirstName().equals(getFirstName()) &&
                    oPerson.getLastName().equals(getLastName()) &&
                    oPerson.getGender().equals(getGender()) &&
                    oPerson.getFatherID().equals(getFatherID()) &&
                    oPerson.getMotherID().equals(getMotherID()) &&
                    oPerson.getSpouseID().equals(getSpouseID());
        } else {
            return false;
        }
    }
}
