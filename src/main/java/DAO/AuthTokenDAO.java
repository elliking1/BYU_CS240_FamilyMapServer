package DAO;

import Model.AuthToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class accesses the AuthToken table and its data
 * @author Cameron Brown
 */
public class AuthTokenDAO {
    /**
     * Database connection
     */
    private Connection myConnection;

    /** Empty constructor for AuthTokenDAO
     *
     * */
    public AuthTokenDAO() {

    }

    /** Constructor for AuthTokenDAO with a connection parameter
     *
     * @param conn Database connection
     * */
    public AuthTokenDAO(Connection conn) {
        this.myConnection = conn;
    }

    /** This method adds a new AuthToken to the database
     *
     * @param newToken The AuthToken to be added
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    public void addToken(AuthToken newToken) throws DatabaseException {
        String sql = "INSERT INTO AuthTokens(Token, AssociatedUserName) VALUES(?,?)";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, newToken.getToken());
            stmt.setString(2, newToken.getUserName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered while inserting user data into the database");
        }
    }

    /** This method removes an AuthToken from the database
     *
     * @param authToken The AuthToken to be removed
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    public void deleteToken(String authToken) throws DatabaseException {
        String sql = "DELETE FROM AuthTokens WHERE Token = ?";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while deleting user");
        }
    }

    /** This method removes all AuthTokens for a specified user from the database
     *
     * @param userName The UserName of the AuthTokens to be removed
     *
     * @throws DatabaseException Error that occurs while trying to access Database
     * */
    public void deleteUsersTokens(String userName) throws DatabaseException {
        String sql = "DELETE FROM AuthTokens WHERE AssociatedUserName = ?";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while deleting user");
        }
    }

    /** This method tries to find an AuthToken in the database
     *
     * @param authToken The AuthToken that is being queried for
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     *
     * @return The Auth Token
     * */
    public AuthToken query(String authToken) throws DatabaseException {
        AuthToken token;
        ResultSet rs = null;
        String sql = "SELECT * FROM AuthTokens WHERE Token = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                token = new AuthToken(rs.getString("Token"), rs.getString("AssociatedUserName"));
                return token;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while finding person");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /** This method tries to find all Auth Tokens in the database for a specific user
     *
     * @param userName The UserName of the user whose tokens are being queried for
     *
     * @throws DatabaseException Error that occurs while trying to access Database
     *
     * @return  All AuthTokens for a specific user
     * */
    public ArrayList<AuthToken> queryUsersTokens(String userName) throws DatabaseException {
        AuthToken token;
        ArrayList<AuthToken> tokens = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM AuthTokens WHERE AssociatedUserName = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                token = new AuthToken(rs.getString("Token"), rs.getString("AssociatedUserName"));
                tokens.add(token);
            }
            return tokens;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while finding person");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
