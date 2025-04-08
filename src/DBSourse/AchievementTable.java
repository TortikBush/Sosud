package DBSourse;
import lombok.Getter;
import lombok.Setter;
public class AchievementTable {

    private  int Id;

    private String Name ;

    private int IdCharacter;

    private String Description;


    public AchievementTable(int id, String name, int idCharacter, String description) {
        Id = id;
        Name = name;
        IdCharacter = idCharacter;
        Description = description;
    }
    public AchievementTable() {

    }

    public void setIdCharacter(int idCharacter) {
        IdCharacter = idCharacter;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public int getId() {
        return Id;
    }
    public String getName() {
        return Name;
    }
    public int getIdCharacter() {
        return IdCharacter;
    }
    public String getDescription() {
        return Description;
    }
    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

}


