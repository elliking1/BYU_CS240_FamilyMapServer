package DAO;
/**
 * This class extends Exceptions and is for exceptions
 * occurring while trying to access the database.
 * @author Cameron Brown
 */
public class DatabaseException extends Exception {

    /**
     * Constructor 1 for DatabaseException
     *
     * @param s Message included with error
     */
    public DatabaseException(String s) {
        super(s);
    }

    /**
     * Constructor 2 for DatabaseException
     *
     * @param s Message included with error
     * @param throwable The cause of the error
     */
    public DatabaseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
