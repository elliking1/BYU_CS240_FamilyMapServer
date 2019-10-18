package DAO;

import Model.Person;
/**
 * This class accesses the Person table and its data in the database
 * @author Cameron Brown
 */
public class PersonDAO {

    /** Empty constructor for PersonDAO
     *
     * */
    public PersonDAO() {

    }

    /** This method adds a new person to the database
     *
     * @param newPerson The person to be added
     * */
    public void addPerson(Person newPerson) {

    }

    /** This method removes a person from the database
     *
     * @param personID The ID of the person to be deleted
     * */
    public void deletePerson(String personID){

    }

    /** This method removes all people from the database
     *
     *  @param userName The User Name of all people to be deleted
     * */
    public void deleteUsersPeople(String userName) {

    }

    /** This method tries to find a person in the database
     *
     * @param personID The ID of the person being queried
     *
     * @return The person being queried
     * */
    public Person query(String personID) {

        return null;
    }

    /** This method tries to find all people in the database for a specific user
     *
     * @param userName The UserName of the user whose people are being queried for
     *
     * @return Person model
     * */
    public Person queryUsersPeople(String userName) {

        return null;
    }
}
