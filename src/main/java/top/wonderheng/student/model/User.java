package top.wonderheng.student.model;

/**
 * @BelongsProject: student-manage-servlet
 * @BelongsPackage: top.wonderheng.student.model
 * @Author: WonderHeng
 * @CreateTime: 2018-09-27 22:40
 */
public class User {

    private int id;
    private String userName;
    private String password;

    public User() {
        super();
    }

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
