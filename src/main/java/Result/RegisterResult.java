package Result;
/**
 * This class handles results for the Register API
 * @author Cameron Brown
 */
public class RegisterResult extends StandardResult {
    /** AuthToken for the user */
    private String authToken;

    /** UserName of the user */
    private String userName;

    /** PersonID of the user */
    private String personID;

    /** Empty constructor for RegisterResult
     *
     * */
    public RegisterResult() {

    }

    /** Constructor for RegisterResult Successes
     *
     * @param authToken AuthToken assigned to the user
     * @param personID PersonID of person assigned to AuthToken
     * @param userName UserName of user assigned to AuthToken
     * */
    public RegisterResult(String authToken, String userName, String personID) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }

    /** Constructor for RegisterRequest Failures
     *
     * @param message Error Message
     * */
    public RegisterResult(String message) {
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

}
