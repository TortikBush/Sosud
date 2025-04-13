package DBSourse;

public class AchievementList {
    private  int Id;
    private String Name ;
    private int IdCharacter;
    private String Description;
    private String NameCharacter;

    public AchievementList() {
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

    public void setIdCharacter(String idCharacter) {
        IdCharacter = Integer.parseInt(idCharacter);
    }

    public String getNameCharacter() {
        return NameCharacter;
    }

    public void setNameCharacter(String nameCharacter) {
        NameCharacter = nameCharacter;
    }
}
