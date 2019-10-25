package Request;
/**
 * This class handles requests to login
 * @author Cameron Brown
 */
public class LoginRequest {
    /** UserName */
    private String userName;
    /** Password */
    private String password;

    /** Empty constructor for LoginRequest
     *
     *  */
    public LoginRequest() {

    }

    /** Non-empty constructor for LoginRequest
     *
     * @param userName UserName of user trying to login in
     * @param password Password of user trying to login in
     *  */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
}
