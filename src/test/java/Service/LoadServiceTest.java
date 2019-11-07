package Service;

import Request.LoadRequest;
import Result.StandardResult;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadServiceTest extends TestParent {
    @Override
    public void setUp()  {

    }

    @Test
    void loadPass() throws Exception {
        File loadFile = new File("json/LoadData.json");
        JsonReader reader = new JsonReader(new FileReader(loadFile));
        Gson g = new Gson();
        LoadRequest loadData = g.fromJson(reader, LoadRequest.class);
        LoadService loadService = new LoadService();
        StandardResult myResult = loadService.load(loadData);
        StandardResult expected = new StandardResult("Successfully added 2 users, 11 persons, and 19 events to the database.");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }

    @Test
    void loadFail() throws Exception {
        File loadFile = new File("json/LoadData.json");
        JsonReader reader = new JsonReader(new FileReader(loadFile));
        Gson g = new Gson();
        LoadRequest loadData = g.fromJson(reader, LoadRequest.class);
        loadData.getEvents().get(0).setCity(null);
        LoadService loadService = new LoadService();
        StandardResult myResult = loadService.load(loadData);
        StandardResult expected = new StandardResult("error - invalid request data");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }
}
