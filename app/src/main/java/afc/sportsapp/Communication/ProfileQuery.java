package afc.sportsapp.Communication;

import java.util.ArrayList;

/**
 * Created by Ludovic PARRA on 2/24/2018.
 * Group AFC.
 */

public class ProfileQuery implements Queryable{

    /**
     * Gives all the profile information of a user in this order: surname, last name, height, weight and mail.
     * @return all the profile information in an ArrayList (surname, last name, height, weight and mail).
     */
    @Override
    public ArrayList<String> getData() {
        ArrayList<String> profile = new ArrayList<String>();

        //TODO: link to database instead of default values
        profile.add("Lucas");
        profile.add("OCHARD");
        profile.add("1.60");
        profile.add("50");
        profile.add("luc.O@gmail.com");

        return profile;
    }
}
