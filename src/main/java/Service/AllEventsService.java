package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseConnect;
import DAO.DatabaseException;
import DAO.EventDAO;
import Model.AuthToken;
import Model.Event;
import Result.AllEventsResult;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class returns ALL events for ALL family members of the current user.
 * @author Cameron Brown
 */
public class AllEventsService {

    /** Empty constructor for AllEventsService
     *
     * */
    public AllEventsService() {

    }

    /** Get All Events tied to current user
     *
     * @return Returns ALL events for ALL family members of the current user
     * @param authToken Auth Token of user whose events these are
     * */
    public AllEventsResult getAllEvents(String authToken) {
        DatabaseConnect dbConnect = new DatabaseConnect();
        try {
            Connection myConnection = dbConnect.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(myConnection);
            AuthToken myToken = authDAO.queryToken(authToken);
            if(myToken == null) {
                dbConnect.closeConnection(false);
                return new AllEventsResult("error - Invalid auth token");
            }

            EventDAO eventDAO = new EventDAO(myConnection);
            ArrayList<Event> events = eventDAO.queryAllEventsOfUser(myToken.getUserName());

            dbConnect.closeConnection(true);
            return new AllEventsResult(events);

        } catch (DatabaseException d) {
            try {
                dbConnect.closeConnection(false);
            } catch (DatabaseException b) {
                b.printStackTrace();
            }
            return new AllEventsResult("Internal server error");
        }
    }
}
