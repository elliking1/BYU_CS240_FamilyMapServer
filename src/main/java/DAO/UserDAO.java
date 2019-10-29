package DAO;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class accesses the User table and its data in the database
 * @author Cameron Brown
 */
public class UserDAO {
    /**
     * Database connection
     */
    private Connection myConnection;

    /** Empty constructor for UserDAO
     *
     * */
    public UserDAO() {

    }

    /** Constructor for UserDAO with a connection parameter
     *
     * @param conn Database connection
     * */
    public UserDAO(Connection conn) {
        this.myConnection = conn;
    }

    /** This method adds a new user to the database
     *
     * @param newUser The user to be added
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    public void addUser(User newUser) throws DatabaseException {
        String sql = "INSERT INTO Users(UserName, Password, Email, FirstName, LastName, Gender, PersonID) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, newUser.getUserName());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getEmail());
            stmt.setString(4, newUser.getFirstName());
            stmt.setString(5, newUser.getLastName());
            stmt.setString(6, newUser.getGender());
            stmt.setString(7, newUser.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error encountered while inserting user data into the database");
        }
    }

    /** This method removes a user from the database
     *
     * @param userName The username of the user to be deleted
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     * */
    public void deleteUser(String userName) throws DatabaseException {
        String sql = "DELETE FROM Users WHERE UserName = ?";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while deleting user");
        }
    }



    /** This method tries to find a user in the database
     *
     * @param userName The username of the user being queried
     *
     * @throws DatabaseException An exception that occurs when there is trouble accessing the database.
     *
     * @return A User
     * */
    public User queryUser(String userName) throws DatabaseException {
        User user;
        ResultSet rs = null;
        String sql = "SELECT * FROM Users WHERE UserName = ?;";
        try (PreparedStatement stmt = myConnection.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("UserName"), rs.getString("Password"),
                        rs.getString("Email"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("Gender"), rs.getString("PersonID"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while finding person");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}
