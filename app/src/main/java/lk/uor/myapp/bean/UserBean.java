package lk.uor.myapp.bean;

public class UserBean {
    private int id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String cotactNo;

    public UserBean() {
    }

    public UserBean(int id, String name, String email, String password, String username, String cotactNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.cotactNo = cotactNo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCotactNo() {
        return cotactNo;
    }

    public void setCotactNo(String cotactNo) {
        this.cotactNo = cotactNo;
    }
}