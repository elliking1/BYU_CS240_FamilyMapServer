package Service;

import DAO.*;
import Model.AuthToken;
import Model.Person;
import Result.PersonResult;

import java.sql.Connection;

/**
 * This class returns the person object with the specified ID
 * @author Cameron Brown
 */
public class PersonService {
    /** Empty Constructor for PersonService
     *
     * */
    public PersonService() {

    }

    /** Finds person object
     *
     * @return Returns person object with specified personID
     *
     * @param personID The PersonID of the person being retrieved
     * @param authToken The Auth Token of the person being retrieved
     * */
    public PersonResult getPerson(String personID, String authToken) {
        try {
            DatabaseConnect dbConnect = new DatabaseConnect();
            Connection myConnection = dbConnect.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(myConnection);
            AuthToken myToken = authDAO.queryToken(authToken);
            if(myToken == null) {
                return new PersonResult("error - Invalid auth token");
            }

            PersonDAO personDAO = new PersonDAO(myConnection);
            Person reqPerson = personDAO.queryPerson(personID);
            if(reqPerson == null) {
                return new PersonResult("error - Invalid personID parameter");
            }

            if(!reqPerson.getAssociatedUserName().equals(myToken.getUserName())) {
                return new PersonResult("error - Requested person does not belong to this user");
            }

            return new PersonResult(reqPerson.getAssociatedUserName(), personID, reqPerson.getFirstName(),
                       reqPerson.getLastName(), reqPerson.getGender(), reqPerson.getFatherID(),
                       reqPerson.getMotherID(), reqPerson.getSpouseID());

        } catch (DatabaseException d) {
            return new PersonResult("Internal server error");
        }
    }
}
