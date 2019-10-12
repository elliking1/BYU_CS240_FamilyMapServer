package Model;

/**
 * This class represents an AuthToken that is paired with a user
 * @author Cameron Brown
 */
public class AuthToken {
    /** The Authentication Token */
    String token;

    /** The UserName of the user the AuthToken is assigned to */
    String userName;

    /** Empty constructor for AuthToken
     *
     * */
    public AuthToken() {

    }

    /** Non-empty constructor for AuthToken
     *
     * */
    public AuthToken(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}