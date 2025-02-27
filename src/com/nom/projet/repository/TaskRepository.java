package com.nom.projet.repository;

import com.nom.projet.db.BDDSQL;
import com.nom.projet.model.Category;
import com.nom.projet.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    /*
     * Attribut
     */
    private static Connection connection = BDDSQL.getConnection();

    /*
     * Méthodes (CRUD)
     */
    //Méthode pour ajouter
    public static Task save(Task addTask){
        //Créer un objet task
        Task newTask = null;
        try {
            //Requête
            String sql = "INSERT INTO task(title, content, user) VALUE(?, ?, ?)";

            //Préparer la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Biend les paramètre
            preparedStatement.setString(1, addTask.getTitle());
            preparedStatement.setString(2, addTask.getContent());
            preparedStatement.setUser(3, addTask.getUser());

            //Exécuter la requête
            int nbrRows = preparedStatement.executeUpdate();

            //Vérifier si la requête est passée
            if(nbrRows > 0){
                newTask = new Task(
                        addTask.getTask()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTask;
    }

    //Méthode pour tester
    public static boolean isExist(String title) {
        boolean getTask = false;
        try {
            String sql = "SELECT id FROM task WHERE title = ?";
            //préparer la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Bind le paramètre
            preparedStatement.setString(1, title);
            //récupérer le resultat de la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            //Vérification du résultat
            while(resultSet.next()){
                getTask = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return getTask;
    }

    //Méthode pour récupérer une task
    public static Task findByTask(String title){
        Category findTask = null;
        try{
            String sql = "SELECT id, title, content, user FROM task WHERE title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,title);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                findTask = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getString("user")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findTask;
    }

    //Méthode pour récupérer toutes les categories dans une List
    public static List<Category> findAll(){
        List<Category> findCategories = new ArrayList<>();
        try {
            String sql = "SELECT id, categoryName FROM category";
            //Préparation de la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Exécution de la requête
            ResultSet resultSet = preparedStatement.executeQuery();
            //Ajout dans la liste des User
            while(resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("categoryName"));
                findCategories.add(category);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return findCategories;
    }
}
