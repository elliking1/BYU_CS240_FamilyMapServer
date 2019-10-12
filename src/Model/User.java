package Model;
/**
 * This class represents a user of the Family Map App
 * @author Cameron Brown
 */
public class User {
    /** UserName of person */
    private String userName;

    /** User's password */
    private String password;

    /** User's email address */
    private String email;

    /** FirstName */
    private String firstName;

    /** LastName */
    private String lastName;

    /** Gender */
    private String gender;

    /** PersonID */
    private String personID;

    /** Empty constructor for User
     *
     * */
    public User() {

    }

    /** Non-empty constructor for User
     *
     * @param userName UserName of user
     * @param password Password of user
     * @param email Email of user
     * @param firstName First name of user
     * @param lastName Last name of user
     * @param gender Gender of user
     * @param personID PersonID of user
     * */
    public User(String userName, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
