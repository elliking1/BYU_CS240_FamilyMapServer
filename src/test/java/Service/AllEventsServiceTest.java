package Service;

import Result.AllEventsResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllEventsServiceTest extends TestParent {
    @Test
    public void getAllEventsPass() throws Exception {
        
    }

    @Test
    public void getAllEventsFail() throws Exception {
        AllEventsService allEventsService = new AllEventsService();
        AllEventsResult myResult = allEventsService.getAllEvents("invalid");
        AllEventsResult expected = new AllEventsResult("error - Invalid auth token");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }
}
