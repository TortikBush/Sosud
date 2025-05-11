package DBSourse;

/**
 * Represents a user in the database with properties like ID, login, password, and role ID.
 * Used for user authentication and authorization.
 */
public class UsersTable {
    /** The unique identifier of the user */
    private  int Id;
    /** The login name of the user */
    private String Login ;
    /** The password of the user */
    private String Password;
    /** The role ID of the user */
    private int IdRole;

    /**
     * Constructor with all parameters
     * 
     * @param id The user ID
     * @param login The user login name
     * @param password The user password
     * @param idRole The user role ID
     */
    public UsersTable(int id, String login, String password, int idRole) {
        this.Id = id;
        this.Login = login;
        this.Password = password;
        this.IdRole = idRole;
    }

    /**
     * Default constructor
     */
    public UsersTable() {
    }

    /**
     * Gets the user ID
     * 
     * @return The user ID
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets the user ID
     * 
     * @param id The user ID to set
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * Gets the user login name
     * 
     * @return The user login name
     */
    public String getLogin() {
        return Login;
    }

    /**
     * Sets the user login name
     * 
     * @param login The user login name to set
     */
    public void setLogin(String login) {
        this.Login = login;
    }

    /**
     * Gets the user password
     * 
     * @return The user password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * Sets the user password
     * 
     * @param password The user password to set
     */
    public void setPassword(String password) {
        this.Password = password;
    }

    /**
     * Gets the user role ID
     * 
     * @return The user role ID
     */
    public int getIdRole() {
        return IdRole;
    }

    /**
     * Sets the user role ID
     * 
     * @param idRole The user role ID to set
     */
    public void setIdRole(int idRole) {
        IdRole = idRole;
    }
}
