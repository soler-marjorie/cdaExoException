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
    private String contente;
    private Date createAt;
    private Date endDate;
    private boolean status;
    private User user;
    private List<Category> categories;

    /*
     * Constructor
     */

    public Task() {
        this.categories = new ArrayList<>();
    };

    public Task(int id, String title, String contente, Date createAt, Date endDate, boolean status) {
        this.id = id;
        this.title = title;
        this.contente = contente;
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

    public String getContente() {
        return contente;
    }

    public void setContente(String contente) {
        this.contente = contente;
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





    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contente='" + contente + '\'' +
                ", createAt=" + createAt +
                ", endDate=" + endDate +
                ", status=" + status +
                ", user=" + user +
                ", categories=" + categories +
                '}';
    }
}
