package Service;

import Request.RegisterRequest;
import Result.RegisterLoginResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegisterServiceTest {
    @Test
    void registerPass() {
        RegisterService registerService = new RegisterService();
        RegisterRequest registerRequest = new RegisterRequest("bobBarker", "cool","bob_barker@thepriceisright.com","Bob","Barker", "m");
        RegisterLoginResult result = registerService.register(registerRequest);
        assertNotNull(result.getAuthToken());
    }

    @Test
    void registerFail() {
        RegisterService registerService = new RegisterService();
        RegisterRequest registerRequest = new RegisterRequest("sheila", "parker","sheila@parker.com","Sheila","Parker","f");
        RegisterLoginResult result = registerService.register(registerRequest);
        RegisterLoginResult expected = new RegisterLoginResult("error - username already taken by another user");
        assertEquals(expected.getMessage(),result.getMessage());
    }
}
