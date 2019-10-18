package DAO;

import Model.AuthToken;

/**
 * This class accesses the AuthToken table and its data
 * @author Cameron Brown
 */
public class AuthTokenDAO {
    /** Empty constructor for AuthTokenDAO
     *
     * */
    public AuthTokenDAO() {

    }

    /** This method adds a new AuthToken to the database
     *
     * @param newToken The AuthToken to be added
     * */
    public void addToken(AuthToken newToken) {

    }

    /** This method removes an AuthToken from the database
     *
     * @param authToken The AuthToken to be removed
     * */
    public void deleteToken(String authToken){

    }

    /** This method removes all AuthTokens for a specified user from the database
     *
     * @param userName The UserName of the AuthTokens to be removed
     * */
    public void deleteUsersTokens(String userName){

    }

    /** This method tries to find an AuthToken in the database
     *
     * @param authToken The AuthToken that is being queried for
     *
     * @return The Auth Token
     * */
    public AuthToken query(String authToken) {

        return null;
    }

    /** This method tries to find all Auth Tokens in the database for a specific user
     *
     * @param userName The UserName of the user whose tokens are being queried for
     *
     * @return Event model
     * */
    public AuthToken queryUsersTokens(String userName) {

        return null;
    }

}
