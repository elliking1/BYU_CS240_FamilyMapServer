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
        DatabaseConnect dbConnect = new DatabaseConnect();
        try {
            Connection myConnection = dbConnect.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(myConnection);
            AuthToken myToken = authDAO.queryToken(authToken);
            if(myToken == null) {
                dbConnect.closeConnection(false);
                return new PersonResult("error - Invalid auth token");
            }

            PersonDAO personDAO = new PersonDAO(myConnection);
            Person reqPerson = personDAO.queryPerson(personID);
            if(reqPerson == null) {
                dbConnect.closeConnection(false);
                return new PersonResult("error - Invalid personID parameter");
            }

            if(!reqPerson.getAssociatedUsername().equals(myToken.getUserName())) {
                dbConnect.closeConnection(false);
                return new PersonResult("error - Requested person does not belong to this user");
            }

            dbConnect.closeConnection(true);
            return new PersonResult(reqPerson.getAssociatedUsername(), personID, reqPerson.getFirstName(),
                       reqPerson.getLastName(), reqPerson.getGender(), reqPerson.getFatherID(),
                       reqPerson.getMotherID(), reqPerson.getSpouseID());

        } catch (DatabaseException d) {
            try {
                dbConnect.closeConnection(false);
            } catch (DatabaseException b) {
                b.printStackTrace();
            }
            return new PersonResult("Internal server error");
        }
    }
}
