package Service;

import Result.AllEventsResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AllEventsServiceTest extends TestParent {
    @Test
    void getAllEventsPass() {
        AllEventsService allEventsService = new AllEventsService();
        AllEventsResult myResult = allEventsService.getAllEvents(registerLoginResult.getAuthToken());
        assertNotNull(myResult);
    }

    @Test
    void getAllEventsFail() {
        AllEventsService allEventsService = new AllEventsService();
        AllEventsResult myResult = allEventsService.getAllEvents("invalid");
        AllEventsResult expected = new AllEventsResult("error - Invalid auth token");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }
}
