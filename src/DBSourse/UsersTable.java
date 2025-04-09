package DBSourse;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UsersTable {
    @Setter
    private  int Id;
    @Setter
    private String Login ;
    @Setter
    private String Password;
    @Setter
    private int IdRole;

    public UsersTable(int id, String login, String password, int idRole) {
        this.Id = id;
        this.Login = login;
        this.Password = password;
        this.IdRole = idRole;
    }

    public UsersTable() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        this.Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public int getIdRole() {
        return IdRole;
    }

    public void setIdRole(int idRole) {
        IdRole = idRole;
    }
}
