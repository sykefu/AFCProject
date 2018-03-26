package afc.sportsapp.model;

/**
 * Created by Ludovic PARRA on 2/27/2018.
 * Group AFC.
 */

import java.util.ArrayList;

/**
 * Model class, holds data relevant to programs.
 */
public class Program {
    private String name;
    private String description;
    private ArrayList<WorkoutGoal> goals;
    private int id;

    public Program(String name, String description, ArrayList<WorkoutGoal> goals, int id) {
        this.name = name;
        this.description = description;
        this.goals = goals;
        this.id = id;
    }

    public Program(String name, String description){
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
