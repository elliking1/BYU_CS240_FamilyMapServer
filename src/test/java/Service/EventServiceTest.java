package Service;

import Result.EventResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventServiceTest extends TestParent {
    @Test
    void getEventPass() {
        EventService eventService = new EventService();
        EventResult myResult = eventService.getEvent("Sheila_Birth", registerLoginResult.getAuthToken());
        EventResult expected = new EventResult("sheila", "Sheila_Birth",
                                               "Sheila_Parker", -36.1833f, 144.9667f,
                                               "Australia", "Melbourne", "birth", 1970);
        assertEquals(myResult, expected);
    }

    @Test
    void getEventFail() {
        EventService eventService = new EventService();
        EventResult myResult = eventService.getEvent("Together_Forever", registerLoginResult.getAuthToken());
        EventResult expected = new EventResult("error - Requested event does not belong to this user");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }
}
