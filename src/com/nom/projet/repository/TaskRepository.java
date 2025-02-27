package com.nom.projet.repository;

import com.nom.projet.db.BDDSQL;
import com.nom.projet.model.Category;
import com.nom.projet.model.Task;
import com.nom.projet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    public static Task findByTitle(String taskTitle, Date createAt){
        Task newTask = new Task();
        try {
            if (isExist(taskTitle)) {
                String sql = "SELECT t.id AS tId,t.content,t.end_date,t.`status`,\n" +
                        "u.id AS uId,u.firstname,u.lastname,\n" +
                        "GROUP_CONCAT(c.id) AS catId, GROUP_CONCAT(c.category_name) AS catName\n" +
                        "FROM task_category AS tc \n" +
                        "INNER JOIN task AS t ON tc.task_id = t.id\n" +
                        "INNER JOIN category AS c ON tc.category_id = c.id\n" +
                        "INNER JOIN users AS u ON t.users_id = u.id\n" +
                        "WHERE t.title = ? AND t.create_at = ?\n" +
                        "GROUP BY t.id";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, taskTitle);
                preparedStatement.setString(2, createAt.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                newTask.setId(resultSet.getInt("tId"));
                newTask.setContent(resultSet.getString("content"));
                newTask.setEndDate(resultSet.getDate("end_date"));
                newTask.setCreateAt(createAt);
                newTask.setStatus(resultSet.getBoolean("status"));
                newTask.setTitle(taskTitle);
                User u = new User();
                u.setId(resultSet.getInt("uId"));
                u.setFirstname(resultSet.getString("firstname"));
                u.setLastname(resultSet.getString("lastname"));
                newTask.setUser(u);
                String[] catIdS = resultSet.getString("catId").split(",");
                String[] catNameS = resultSet.getString("catName").split(",");
                for(int i = 0;i<catIdS.length;i++) {
                    Category c = new Category();
                    c.setId(Integer.parseInt(catIdS[i]));
                    c.setCategoryName(catNameS[i]);
                    newTask.addCategory(c);
                }
            } else {
                System.out.println("Ce compte n'existe pas.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTask;
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
