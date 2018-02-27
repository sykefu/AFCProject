package afc.sportsapp.Communication;

import java.util.ArrayList;

/**
 * Created by Ludovic PARRA on 2/24/2018.
 * Group AFC.
 */

/**
 * Database queries relevant to the profile activity.
 */
public class ProfileQuery{

    /**
     * Gives all the profile information of a user in this order: surname, last name, height, weight and mail.
     * @return all the profile information in an ArrayList (surname, last name, height, weight and mail).
     */

    public ArrayList<String> getData() {
        //dummy code for testing
        ArrayList<String> profile = new ArrayList<String>();
        profile.add("Lucas");
        profile.add("OCHARD");
        profile.add("1.60");
        profile.add("50");
        profile.add("luc.O@gmail.com");

        return profile;
    }
}
