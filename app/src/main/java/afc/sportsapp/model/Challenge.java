package afc.sportsapp.model;

/**
 * Created by Ludovic PARRA on 2/27/2018.
 * Group AFC.
 */

/**
 * Model class, holds data relevant to challlenges.
 */
public class Challenge {
    private String name;
    private String description;
    //later add relevant data needed for challenges.

    /**
     * Constructor for challenges.
     * @param name the name of the challenge
     * @param description a description of the challenge
     */
    public Challenge(String name, String description){
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
