package com.nom.projet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    /*
     * ATTRIBUTS
     */
    private int id;
    private String title;
    private String content;
    private Date createAt;
    private Date endDate;
    private boolean status;
    private User user;
    private List<Category> categories;

    /*
     * Constructor
     */

    public Task(int id, String title, String content, String user) {
        this.categories = new ArrayList<>();
    };

    public Task(int id, String title, String content, Date createAt, Date endDate, boolean status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.endDate = endDate;
        this.status = status;
        this.categories = new ArrayList<>();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = contente;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category){
        this.categories.add(category);
    }

    public void removeCategory(Category category){
        this.categories.remove(category);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
