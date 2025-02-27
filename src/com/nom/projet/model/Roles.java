package com.nom.projet.model;

public class Roles {
    /*
     * ATTRIBUTS
     */
    private int id;
    private String rolesName;

    /*
     * CONSTRUCTOR
     */

    public Roles() {};

    public Roles(String rolesName) {
        this.rolesName = rolesName;
    }

    /*
     * GETTERS SETTERS
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", rolesName='" + rolesName + '\'' +
                '}';
    }
}
