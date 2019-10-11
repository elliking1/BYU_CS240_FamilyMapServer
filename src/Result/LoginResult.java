package Result;
/**
 * This class handles results for the Login API
 * @author Cameron Brown
 */
public class LoginResult {

    /** AuthToken for the user */
    private String authToken;

    /** UserName of the user */
    private String userName;

    /** PersonID of the user */
    private String personID;

    /** Error Message */
    private String message;


    /** Empty constructor for LoginResult
     *
     * */
    public LoginResult() {

    }

    /** Constructor for LoginResult Successes
     *
     * */
    public LoginResult(String authToken, String userName, String personID) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }

    /** Constructor for LoginResult Failures
     *
     * */
    public LoginResult(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
