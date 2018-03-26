package afc.sportsapp.model;

/**
 * Created by Ludovic PARRA on 3/17/2018.
 * Group AFC.
 */

public class IDData {
    static private int programId;
    static private int programProgression; //maybe query this from web
    static private int challengeId;
    static private int userId;

    public static int getProgramId() {
        return programId;
    }

    public static void setProgramId(int programId) {
        IDData.programId = programId;
    }

    public static int getProgramProgression() {
        return programProgression;
    }

    public static void setProgramProgression(int programProgression) {
        IDData.programProgression = programProgression;
    }

    public static int getChallengeId() {
        return challengeId;
    }

    public static void setChallengeId(int challengeId) {
        IDData.challengeId = challengeId;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        IDData.userId = userId;
    }

    private IDData(){}
}
