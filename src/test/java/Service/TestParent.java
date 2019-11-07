package Service;

import DAO.AuthTokenDAO;
import Request.LoadRequest;
import Request.LoginRequest;
import Result.RegisterLoginResult;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileReader;

public class TestParent {
    // The result of logging the user in so that we have easy access to AuthToken in testing.
    protected RegisterLoginResult registerLoginResult;

    @BeforeEach
    public void setUp() throws Exception {
        // Load data into database for testing
        File loadFile = new File("json/LoadData.json");
        JsonReader reader = new JsonReader(new FileReader(loadFile));
        Gson g = new Gson();
        LoadRequest loadData = g.fromJson(reader, LoadRequest.class);
        LoadService serviceObject = new LoadService();
        serviceObject.load(loadData);

        // Login a user to create an AuthToken
        LoginRequest loginRequest = new LoginRequest("sheila", "parker");
        LoginService loginService = new LoginService();
        registerLoginResult = loginService.login(loginRequest);
    }

   /* @AfterEach
    public void tearDown() throws Exception {

    }*/
}
