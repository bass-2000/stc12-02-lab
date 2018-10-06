package ru.innopolis.stc12.lab02.pojo;

public class Role {
    private int id;
    private String roleDescription;

    public Role(int id, String roleDescription) {
        this.id = id;
        this.roleDescription = roleDescription;
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(String role) {
        this.roleDescription = roleDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
