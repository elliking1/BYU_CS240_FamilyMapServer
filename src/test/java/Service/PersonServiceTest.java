package Service;

import Result.PersonResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest extends TestParent {

    @Test
    public void getPersonPass() throws Exception {
        PersonService personService = new PersonService();
        PersonResult myResult = personService.getPerson("Sheila_Parker", registerLoginResult.getAuthToken());
        PersonResult expected = new PersonResult("sheila", "Sheila_Parker",
                "Sheila", "Parker", "f",
                "Blaine_McGary", "Betty_White", "Davis_Hyer");
        assertEquals(myResult, expected);
    }

    @Test
    public void getPersonFail() throws Exception {
        PersonService personService = new PersonService();
        PersonResult myResult = personService.getPerson("Happy_Birthday", registerLoginResult.getAuthToken());
        PersonResult expected = new PersonResult("error - Requested person does not belong to this user");
        assertEquals(myResult.getMessage(), expected.getMessage());
    }


}
