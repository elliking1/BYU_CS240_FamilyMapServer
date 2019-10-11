package Result;
/**
 * This class handles results of generating ancestors
 * @author Cameron Brown
 */
public class FillResult {
    /** Success/Error Message */
    private String message;

    /** Empty constructor for FillResult
     *
     * */
    public FillResult() {

    }

    /** Non-empty constructor for FillResult
     *
     * */
    public FillResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
