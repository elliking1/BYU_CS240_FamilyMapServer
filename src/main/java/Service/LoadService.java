package Service;

import Request.LoadRequest;
import Result.LoadResult;

/**
 * This class clears all data from the database and then loads
 * the posted user, person, and event data into the database.
 * @author Cameron Brown
 */
public class LoadService {
    /** Empty Constructor for LoadService
     *
     * */
    public LoadService() {

    }

    /** Clears data and loads new data into database
     *
     * @return Returns message of the success or error of the Load Operation
     *
     * @param request Takes data to be loaded into database
     * */
    public LoadResult load(LoadRequest request) {

        return null;
    }
}
