package DAO;

import Model.AuthToken;

import java.sql.*;

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
        String sql = "INSERT INTO AuthTokens(Token, AssociatedUserName) VALUES(?,?);";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, newToken.getToken());
            stmt.setString(2, newToken.getUserName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered while inserting token into the database");
        }
    }

    /** This method removes an AuthToken from the database
     *
     * @param authToken The AuthToken to be removed
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    void deleteToken(String authToken) throws DatabaseException {
        String sql = "DELETE FROM AuthTokens WHERE Token = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while deleting token");
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
    public AuthToken queryToken(String authToken) throws DatabaseException {
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
            throw new DatabaseException("Error encountered while finding token");
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

    /** This method clears the entire AuthTokens table
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    void clearAuthTokensTable() throws DatabaseException {
        try (Statement stmt = myConnection.createStatement()){
            String sql = "DELETE FROM AuthTokens;";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DatabaseException("SQL Error encountered while clearing tables");
        }
    }

}
