package Service;

import Result.AllPeopleResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AllPeopleServiceTest extends TestParent {
    @Test
    void getAllPeoplePass() {
        AllPeopleService allPeopleService = new AllPeopleService();
        AllPeopleResult myResult = allPeopleService.getAllPeople(registerLoginResult.getAuthToken());
        assertNotNull(myResult.getData());
    }

    @Test
    void getAllPeopleFail() {
        AllPeopleService allPeopleService = new AllPeopleService();
        AllPeopleResult myResult = allPeopleService.getAllPeople("invalid");
        AllPeopleResult expected = new AllPeopleResult("error - Invalid auth token");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }
}
