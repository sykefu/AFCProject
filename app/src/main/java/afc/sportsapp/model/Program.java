package afc.sportsapp.model;

/**
 * Created by Ludovic PARRA on 2/27/2018.
 * Group AFC.
 */

/**
 * Model class, holds data relevant to programs.
 */
public class Program {
    private String name;
    private String description;

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
}
