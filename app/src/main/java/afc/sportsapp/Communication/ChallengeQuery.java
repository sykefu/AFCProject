package afc.sportsapp.Communication;

/**
 * Created by Ludovic PARRA on 2/27/2018.
 * Group AFC.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;

import afc.sportsapp.model.Challenge;

/**
 * Database queries relevant to the challenge activity.
 */
public class ChallengeQuery {

    /**
     * Gives all the challenges accepted by the user.
     * @return contains a list of the challenges.
     */
    public ArrayList<Challenge> getChallenges(){
        //dummy code for testing
        ArrayList<Challenge> al = new ArrayList<Challenge>();
        al.add(new Challenge("sprint 500m", "essayez de faire le meilleur temps !"));
        al.add(new Challenge("Footing des potes", "qui fais 5 km le plus rapidement ?"));
        return al;
    }
}
