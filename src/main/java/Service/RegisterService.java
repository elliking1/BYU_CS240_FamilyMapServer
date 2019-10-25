package Service;

import Request.RegisterRequest;
import Result.RegisterResult;

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
    public RegisterResult login(RegisterRequest request) {

        return null;
    }
}
