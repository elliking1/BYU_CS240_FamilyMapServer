package Service;

import DAO.AuthTokenDAO;
import DAO.DatabaseConnect;
import DAO.DatabaseException;
import DAO.EventDAO;
import Model.AuthToken;
import Model.Event;
import Result.EventResult;

import java.sql.Connection;

/**
 * This class returns an event object with the specified ID.
 * @author Cameron Brown
 */
public class EventService {
    public EventService() {

    }

    /** Find specified event
     *
     * @return Returns specified event
     * @param eventID The unique ID of the event to be returned
     * @param authToken The Auth Token of the person's event that is being retrieved
     * */
    public EventResult getEvent(String eventID, String authToken) {
        DatabaseConnect dbConnect = new DatabaseConnect();
        try {
            Connection myConnection = dbConnect.openConnection();
            AuthTokenDAO authDAO = new AuthTokenDAO(myConnection);
            AuthToken myToken = authDAO.queryToken(authToken);
            if(myToken == null) {
                dbConnect.closeConnection(false);
                return new EventResult("error - Invalid auth token");
            }

            EventDAO eventDAO = new EventDAO(myConnection);
            Event reqEvent = eventDAO.queryEvent(eventID);
            if(reqEvent == null) {
                dbConnect.closeConnection(false);
                return new EventResult("error - Invalid eventID parameter");
            }

            if(!reqEvent.getAssociatedUsername().equals(myToken.getUserName())) {
                dbConnect.closeConnection(false);
                return new EventResult("error - Requested event does not belong to this user");
            }

            dbConnect.closeConnection(true);
            return new EventResult(reqEvent.getAssociatedUsername(), eventID, reqEvent.getPersonID(), reqEvent.getLatitude(),
                    reqEvent.getLongitude(), reqEvent.getCountry(), reqEvent.getCity(),
                    reqEvent.getEventType(), reqEvent.getYear());

        } catch (DatabaseException d) {
            try {
                dbConnect.closeConnection(false);
            } catch (DatabaseException b) {
                b.printStackTrace();
            }
            return new EventResult("Internal server error");
        }
    }
}
