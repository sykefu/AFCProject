package afc.sportsapp.Communication;

/**
 * Created by Ludovic PARRA on 2/27/2018.
 * Group AFC.
 */

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import afc.sportsapp.R;
import afc.sportsapp.model.Program;
import afc.sportsapp.model.WorkoutGoal;

/**
 * Database queries relevant to the Program activity.
 */
public class ProgramQuery {

    private Context ctx;

    public ProgramQuery(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Gives all the challenges accepted by the user.
     * @return contains a list of the challenges.
     */


    public ArrayList<Program> getPrograms(){
        //dummy code for testing
        ArrayList<Program> al = new ArrayList<Program>();
        al.add(getProgram(R.raw.course_remise_au_sport));
        return al;
    }

    public Program getProgram(int id){
        InputStream inputStream = ctx.getResources().openRawResource(id);


        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String title;
        String description;
        ArrayList<WorkoutGoal> workoutGoals = new ArrayList<WorkoutGoal>();
        String temp;
        StringBuilder text = new StringBuilder();

        try {
            title = buffreader.readLine();
            description = buffreader.readLine();
            while (( temp = buffreader.readLine()) != null) {
            String[] numbersArray = temp.split(" ");
            temp = buffreader.readLine();
            workoutGoals.add(new WorkoutGoal(Integer.parseInt(numbersArray[0]),Integer.parseInt(numbersArray[1]),Integer.parseInt(numbersArray[2]),temp ));
            }
        } catch (IOException e) {
            return null;
        }
        return new Program(title, description, workoutGoals, id);
    }
}
