package ru.innopolis.stc12.lab02.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Role role;
    private Integer chiefId;
    private Integer salary;

    public User(Integer id, String username, String password, Role role, Integer chiefId, Integer salary) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.chiefId = chiefId;
        this.salary = salary;
    }

    public User(String username, String password, Role role, Integer chiefId, Integer salary) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.chiefId = chiefId;
        this.salary = salary;
    }

    public User(Integer id, String username, Role role, Integer chiefId, Integer salary) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.chiefId = chiefId;
        this.salary = salary;
    }

    public User(String username, Role role, Integer chiefId, Integer salary) {
        this.username = username;
        this.role = role;
        this.chiefId = chiefId;
        this.salary = salary;
    }

    public User(String username, Role role, Integer salary) {
        this.username = username;
        this.role = role;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", chiefId=" + chiefId +
                ", salary=" + salary +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRoleId(Role role) {
        this.role = role;
    }

    public Integer getChiefId() {
        return chiefId;
    }

    public void setChiefId(Integer chiefId) {
        this.chiefId = chiefId;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
