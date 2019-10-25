package Service;

import Result.FillResult;

/**
 * This class fills the database
 * @author Cameron Brown
 */
public class FillService {
    /** Empty Constructor for FillService
     *
     * */
    public FillService() {

    }

    /** Overloaded method. This version fills database with the specified amount
     * of generations for the specified user.
     *
     * @param userName The UserName of the person whose generation data will fill the database.
     * @param generations The number of generations of the user's ancestors to generate.
     *
     * @return Returns the success or failure of the clear operation
     * */
    public FillResult fillDatabase(String userName, int generations) {

        return null;
    }

    /** Overloaded method. This version allows the generation parameter
     *  to be optional and fills the database with 4 generations
     *  of the user's ancestors.
     *
     * @param userName The UserName of the person whose generation data will fill the database.
     *
     * @return Returns the success or failure of the clear operation
     * */
    public FillResult fillDatabase(String userName) {

        return null;
    }
}
