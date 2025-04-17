package HelpClasses;

public class Users {
    public static int IdUser;
    public static String UserName;
    public static String SavePoint;
    public static Boolean MusicActive = true;
    public static Boolean SoundActive = true;

    public static String GetUserName(){
        return UserName;
    }

    public static int GetIdUser() {
        return IdUser;
    }

    public static String GetSavePoint() {
        return SavePoint;
    }

    public static Boolean GetMusicActive() {
        return MusicActive;
    }

    public static Boolean GetSoundActive() {
        return SoundActive;
    }

}
