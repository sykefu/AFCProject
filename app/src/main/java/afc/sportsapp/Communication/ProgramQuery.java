package afc.sportsapp.Communication;

/**
 * Created by Ludovic PARRA on 2/27/2018.
 * Group AFC.
 */

import java.util.ArrayList;

import afc.sportsapp.model.Program;

/**
 * Database queries relevant to the Program activity.
 */
public class ProgramQuery {

    /**
     * Gives all the challenges accepted by the user.
     * @return contains a list of the challenges.
     */
    public ArrayList<Program> getPrograms(){
        //dummy code for testing
        ArrayList<Program> al = new ArrayList<Program>();
        al.add(new Program("Remise en forme", "Programme de remise en forme progressif pour tout le monde."));
        al.add(new Program("Entrainement marathon", "Programme intensif ayant pour but de préparer à un marathon."));
        return al;
    }
}
