package Model;

import java.util.UUID;

/**
 * This class represents an AuthToken that is paired with a user
 * @author Cameron Brown
 */
public class AuthToken {
    /** The Authentication Token */
    private String token;

    /** The UserName of the user the AuthToken is assigned to */
    private String userName;

    /** Empty constructor for AuthToken
     *
     * */
    public AuthToken() {

    }

    /** UserName parameter constructor for AuthToken
     *
     * @param userName UserName of User Associated with AuthToken
     * */
    public AuthToken(String userName) {
        this.token = UUID.randomUUID().toString();
        this.userName = userName;
    }

    /** All Parameters constructor for AuthToken
     *
     * @param token AuthToken object
     * @param userName UserName of User Associated with AuthToken
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

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof AuthToken) {
            AuthToken oAuthToken = (AuthToken) o;
            return  oAuthToken.getToken().equals(getToken()) &&
                    oAuthToken.getUserName().equals(getUserName());
        } else {
            return false;
        }
    }
}
