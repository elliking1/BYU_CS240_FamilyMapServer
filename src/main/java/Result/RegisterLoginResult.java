package Result;
/**
 * This class handles results for the Login API
 * @author Cameron Brown
 */
public class RegisterLoginResult extends StandardResult {

    /** AuthToken for the user */
    private String authToken;

    /** UserName of the user */
    private String userName;

    /** PersonID of the user */
    private String personID;

    /** Empty constructor for RegisterLoginResult
     *
     * */
    public RegisterLoginResult() {

    }

    /** Constructor for RegisterLoginResult Successes
     *
     * @param authToken AuthToken assigned to the user
     * @param personID PersonID of person assigned to AuthToken
     * @param userName UserName of user assigned to AuthToken
     * */
    public RegisterLoginResult(String authToken, String userName, String personID) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }

    /** Constructor for RegisterLoginResult Failures
     *
     * @param message Error message
     * */
    public RegisterLoginResult(String message) {
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
