package Result;
/**
 * This class handles the result of clearing the database
 * @author Cameron Brown
 */
public class ClearResult {
    /** Success/Error Message */
    private String message;

    /** Empty constructor for ClearResult
     *
     * */
    public ClearResult() {

    }

    /** Non-empty constructor for ClearResult
     *
     * @param message Success/Error Message
     * */
    public ClearResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
