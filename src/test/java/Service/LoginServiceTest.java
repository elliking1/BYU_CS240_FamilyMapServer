package Service;

import Request.LoginRequest;
import Result.RegisterLoginResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoginServiceTest extends TestParent {
    @Test
    void loginPass() {
        LoginService loginService = new LoginService();
        LoginRequest loginRequest = new LoginRequest("sheila", "parker");
        RegisterLoginResult result = loginService.login(loginRequest);
        assertNotNull(result.getAuthToken());
    }

    @Test
    void loginFail() {
        LoginService loginService = new LoginService();
        LoginRequest loginRequest = new LoginRequest("bob", "parker");
        RegisterLoginResult result = loginService.login(loginRequest);
        RegisterLoginResult expected = new RegisterLoginResult("error - Failed to login. Login information was incorrect.");
        assertEquals(expected.getMessage(),result.getMessage());
    }
}
