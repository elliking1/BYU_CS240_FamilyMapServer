package Result;

import com.google.gson.annotations.Expose;

/**
 * This class handles all standard results that don't require a unique implementation
 * and serves as parent class for results that do need a unique implementation.
 * @author Cameron Brown
 */
public class StandardResult {
    /** Success/Error Message */
    @Expose
    protected String message;

    /** Empty constructor for LoadResult
     *
     * */
    public StandardResult() {

    }

    /** Non-empty constructor for LoadResult
     *
     * @param message Success or Error Message
     * */
    public StandardResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
