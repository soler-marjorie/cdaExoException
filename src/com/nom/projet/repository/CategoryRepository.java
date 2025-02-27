package com.nom.projet.repository;

import com.nom.projet.db.BDDSQL;
import com.nom.projet.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    /*
     * Attribut
     */
    private static Connection connection = BDDSQL.getConnection();

    /*
     * Méthodes (CRUD)
     */
    //Méthode pour ajouter
    public static Category save(Category addCategory){
        //Créer un objet user
        Category newCategory = null;
        try {
            //Requête
            String sql = "INSERT INTO category(categoryName) VALUE(?)";

            //Préparer la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Biend les paramètre
            preparedStatement.setString(1, addCategory.getCategoryName());

            //Exécuter la requête
            int nbrRows = preparedStatement.executeUpdate();

            //Vérifier si la requête est passée
            if(nbrRows > 0){
                newCategory = new Category(
                        resultSet.getInt("id"), addCategory.getCategoryName()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCategory;
    }

    //Méthode pour tester
    public static boolean isExist(String categoryName) {
        boolean getCategory = false;
        try {
            String sql = "SELECT id FROM category WHERE categoryName = ?";
            //préparer la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Bind le paramètre
            preparedStatement.setString(1, categoryName);
            //récupérer le resultat de la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            //Vérification du résultat
            while(resultSet.next()){
                getCategory = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return getCategory;
    }

    //Méthode pour récupérer une catégorie
    public static Category findByCategory(String categoryName){
        Category findCategory = null;
        try{
            String sql = "SELECT id, categoryName FROM category WHERE categoryName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,categoryName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                findCategory = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("categoryName")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findCategory;
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
