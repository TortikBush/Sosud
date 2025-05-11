package DBSourse;

public class AchievementList {
    private  int Id;
    private String Name ;
    private int IdCharacter;
    private String Description;
    private String NameCharacter;

    /**
     * Default constructor for AchievementList
     */
    public AchievementList() {
    }

    /**
     * Sets the character ID
     * @param idCharacter The ID of the character
     */
    public void setIdCharacter(int idCharacter) {
        IdCharacter = idCharacter;
    }

    /**
     * Sets the achievement description
     * @param description The description of the achievement
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Gets the achievement ID
     * @return The ID of the achievement
     */
    public int getId() {
        return Id;
    }

    /**
     * Gets the achievement name
     * @return The name of the achievement
     */
    public String getName() {
        return Name;
    }

    /**
     * Gets the character ID
     * @return The ID of the character
     */
    public int getIdCharacter() {
        return IdCharacter;
    }

    /**
     * Gets the achievement description
     * @return The description of the achievement
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Sets the achievement ID
     * @param id The ID to set
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Sets the achievement name
     * @param name The name to set
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Sets the character ID from a string value
     * @param idCharacter The character ID as a string
     */
    public void setIdCharacter(String idCharacter) {
        IdCharacter = Integer.parseInt(idCharacter);
    }

    /**
     * Gets the character name
     * @return The name of the character
     */
    public String getNameCharacter() {
        return NameCharacter;
    }

    /**
     * Sets the character name
     * @param nameCharacter The name of the character
     */
    public void setNameCharacter(String nameCharacter) {
        NameCharacter = nameCharacter;
    }
}
