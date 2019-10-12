package DAO;
/**
 * This class extends Exception for DatabaseExceptions
 * @author Cameron Brown
 */
public class DatabaseException extends Exception {

    /**
     * Constructor 1 for DatabaseException
     */
    public DatabaseException(String s) {
        super(s);
    }

    /**
     * Constructor 2 for DatabaseException
     */
    public DatabaseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
