package HelpClasses;

/**
 * Utility class that stores and provides access to user information and settings.
 * Contains static variables and getter methods for user data.
 */
public class Users {
    /** The ID of the current user */
    public static int IdUser;
    /** The name of the current user */
    public static String UserName;
    /** The save point of the current user in the story */
    public static String SavePoint;
    /** Flag indicating whether music is active for the current user */
    public static Boolean MusicActive;
    /** Flag indicating whether sound is active for the current user */
    public static Boolean SoundActive;

    /**
     * Gets the name of the current user
     * 
     * @return The user's name, or null if not set
     */
    public static String GetUserName(){
        return UserName;
    }

    /**
     * Gets the ID of the current user
     * 
     * @return The user's ID
     */
    public static int GetIdUser() {
        return IdUser;
    }

    /**
     * Gets the save point of the current user
     * 
     * @return The user's save point in the story
     */
    public static String GetSavePoint() {
        return SavePoint;
    }

    /**
     * Gets whether music is active for the current user
     * 
     * @return True if music is active, false otherwise
     */
    public static Boolean GetMusicActive() {
        return MusicActive;
    }

    /**
     * Gets whether sound is active for the current user
     * 
     * @return True if sound is active, false otherwise
     */
    public static Boolean GetSoundActive() {
        return SoundActive;
    }

}
