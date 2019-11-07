package Service;

import DAO.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Request.RegisterRequest;
import Result.RegisterLoginResult;

import java.sql.Connection;

/**
 * This class creates a new user, generates 4 generations of ancestor data
 * for the new user, logs the user in, and returns an AuthToken.
 * @author Cameron Brown
 */
public class RegisterService {
    /** Empty Constructor for RegisterService
     *
     * */
    public RegisterService() {

    }

    /** Registers a user
     *
     * @return Returns AuthToken with UserName and PersonID if successful or error message if failure
     *
     * @param request Takes a request to set up a new user
     * */
    public RegisterLoginResult register(RegisterRequest request) {
        DatabaseConnect dbConnect = new DatabaseConnect();
        try {
            Connection myConnection = dbConnect.openConnection();
            UserDAO userDAO = new UserDAO(myConnection);
            User reqUser = userDAO.queryUser(request.getUserName());
            if(reqUser != null) {
                dbConnect.closeConnection(false);
                return new RegisterLoginResult("error - username already taken by another user");
            }
            PersonDAO personDAO = new PersonDAO(myConnection);

            try {
                Person newPerson = new Person(request.getUserName(), request.getFirstName(),
                        request.getLastName(), request.getGender());
                personDAO.addPerson(newPerson);
                User newUser = new User(request.getUserName(), request.getPassword(), request.getEmail(),
                        request.getFirstName(), request.getLastName(), request.getGender(),
                        newPerson.getPersonID());
                userDAO.addUser(newUser);
                AuthTokenDAO authTokenDAO = new AuthTokenDAO(myConnection);
                AuthToken newToken = new AuthToken(newUser.getUserName());
                authTokenDAO.addToken(newToken);

                dbConnect.closeConnection(true);

                FillService fillService = new FillService();
                fillService.fillDatabase(newUser.getUserName(), 4);

                return new RegisterLoginResult(newToken.getToken(), newUser.getUserName(), newUser.getPersonID());
            } catch (DatabaseException wrongInfo) {
                System.out.println(wrongInfo.getMessage());
                try {
                    dbConnect.closeConnection(false);
                } catch (DatabaseException b) {
                    b.printStackTrace();
                }
                return new RegisterLoginResult("error - Request property missing or has invalid value");
            }
        } catch (DatabaseException d) {
            try {
                dbConnect.closeConnection(false);
            } catch (DatabaseException b) {
                b.printStackTrace();
            }
            return new RegisterLoginResult("Internal server error");
        }
    }
}
