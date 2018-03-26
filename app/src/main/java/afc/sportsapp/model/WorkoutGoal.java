package afc.sportsapp.model;

/**
 * Created by Ludovic PARRA on 3/17/2018.
 * Group AFC.
 */

/**
 * To be used as part of Program to define what is to be done.
 */
public class WorkoutGoal {
    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public int getDaysBeforeNext() {
        return daysBeforeNext;
    }

    /**
     * how much distance was covered. in meters.
     */
    private int distance;
    /**
     * how long the workout took.
     */
    private int time;
    /**
     * time in day before the next training day
     */
    private int daysBeforeNext;
    /**
     * instructions for the run.
     */
    private String instructions;
    /**
     * distance to run in meters if specified, otherwise -1.
     */
    private int distanceGoal;
    /**
     * time in minute until end of training, if unlimited -1.
     */

    private int MaxTime;


    public WorkoutGoal(int daysBeforeNext, int maxTime, int distanceGoal, String instructions) {
        this.distance = 0;
        this.time = 0;
        this.daysBeforeNext = daysBeforeNext;
        this.MaxTime = maxTime;
        this.distanceGoal = distanceGoal;
        this.instructions = instructions;
    }
}
