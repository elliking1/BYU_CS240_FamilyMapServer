package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseConnect;
import DAO.DatabaseException;
import DAO.UserDAO;
import Model.AuthToken;
import Model.User;
import Request.LoginRequest;
import Result.RegisterLoginResult;
import java.util.UUID;

import java.sql.Connection;

/**
 * This class logs a user in and returns an AuthToken
 * @author Cameron Brown
 */
public class LoginService {
    /** Empty constructor for LoginService
     *
     * */
    public LoginService() {

    }

    /** Logs user in
     *
     * @return Returns AuthToken with UserName and PersonID if successful or error message if failure
     *
     * @param request The data of the user that is requesting to log in
     * */
    public RegisterLoginResult login(LoginRequest request) {
        try {
            DatabaseConnect dbConnect = new DatabaseConnect();
            Connection myConnection = dbConnect.openConnection();
            UserDAO userDAO = new UserDAO(myConnection);
            User reqUser = userDAO.queryUser(request.getUserName());

            if (reqUser != null && reqUser.getPassword().equals(request.getPassword())) {
                AuthTokenDAO authDAO = new AuthTokenDAO(myConnection);
                AuthToken myToken = authDAO.queryToken(request.getUserName());
                if(myToken == null) {
                    myToken = new AuthToken(reqUser.getUserName());
                    authDAO.addToken(myToken);
                }
                return new RegisterLoginResult(myToken.getToken(), reqUser.getUserName(), reqUser.getPersonID());
            }
            else {
                return new RegisterLoginResult("Error: Failed to login. Login information was incorrect.");
            }
        } catch (DatabaseException d) {
            return new RegisterLoginResult("Internal server error");
        }
    }

}
