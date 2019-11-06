package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseConnect;
import DAO.DatabaseException;
import DAO.PersonDAO;
import Model.AuthToken;
import Model.Person;
import Result.AllPeopleResult;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class returns ALL family members of the current user.
 * @author Cameron Brown
 */
public class AllPeopleService {
    /** Empty constructor for AllPeopleService
     *
     * */
    public AllPeopleService() {

    }

    /** Get All People tied to current user
     *
     * @return Returns ALL family members of the current user
     * @param authToken Auth Token of user whose relatives these are
     * */
    public AllPeopleResult getAllPeople(String authToken) {
        DatabaseConnect dbConnect = new DatabaseConnect();
        try {
            Connection myConnection = dbConnect.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(myConnection);
            AuthToken myToken = authDAO.queryToken(authToken);
            if(myToken == null) {
                dbConnect.closeConnection(false);
                return new AllPeopleResult("error - Invalid auth token");
            }

            PersonDAO personDAO = new PersonDAO(myConnection);
            ArrayList<Person> people = personDAO.queryAllRelativesOfUser(myToken.getUserName());

            dbConnect.closeConnection(true);
            return new AllPeopleResult(people);

        } catch (DatabaseException d) {
            try {
                dbConnect.closeConnection(false);
            } catch (DatabaseException b) {
                b.printStackTrace();
            }
            return new AllPeopleResult("Internal server error");
        }
    }
}
