package Service;

import Result.StandardResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FillServiceTest {
    @Test
    void fillDatabasePass() {
        FillService fillService = new FillService();
        StandardResult myResult = fillService.fillDatabase("sheila", 4);
        StandardResult expected = new StandardResult("Successfully added 31 persons and 91 events to the database.");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }

    @Test
    void fillDatabaseFail() {
        FillService fillService = new FillService();
        StandardResult myResult = fillService.fillDatabase("bob", 4);
        StandardResult expected = new StandardResult("error - Invalid username.");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }
}
