package com.nom.projet.model;

public class Category {
    /*
     * ATTRIBUTS
     */
    private int id;
    private String categoryName;

    /*
     * CONSTRUCTOR
     */

    public Category() {};

    /*
     * GETTERS SETTERS
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
