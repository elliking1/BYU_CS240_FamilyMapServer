package Service;

import Result.StandardResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClearServiceTest {
    @Test
    void clearDatabasePass() {
        ClearService clearService = new ClearService();
        StandardResult myResult = clearService.clearDatabase();
        StandardResult expected = new StandardResult("Clear succeeded.");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }

    @Test
    void clearDatabasePass2() {
        ClearService clearService = new ClearService();
        StandardResult myResult = clearService.clearDatabase();
        assertNotNull(myResult);
    }
}
