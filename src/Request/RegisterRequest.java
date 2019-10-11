package Request;
/**
 * This class handles requests to register
 * @author Cameron Brown
 */
public class RegisterRequest {
    /** UserName */
    private String userName;

    /** Password */
    private String password;

    /** Email */
    private String email;

    /** FirstName */
    private String firstName;

    /** LastName */
    private String lastName;

    /** Gender */
    private String gender;

    /** Empty constructor for RegisterRequest
     *
     *  */
    public RegisterRequest() {

    }

    /** Non-empty constructor for RegisterRequest
     *
     *  */
    public RegisterRequest(String userName, String password, String email, String firstName, String lastName, String gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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
}
