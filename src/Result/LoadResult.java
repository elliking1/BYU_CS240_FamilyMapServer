package Result;
/**
 * This class handles results of loading data into the database.
 * @author Cameron Brown
 */
public class LoadResult {
    /** Success/Error Message */
    private String message;

    /** Empty constructor for LoadResult
     *
     * */
    public LoadResult() {

    }

    /** Non-empty constructor for LoadResult
     *
     * @param message Success or Error Message
     * */
    public LoadResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
