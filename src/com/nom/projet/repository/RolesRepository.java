package com.nom.projet.repository;

import com.nom.projet.db.BDDSQL;
import com.nom.projet.model.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesRepository {
    /*
     * Attribut
     */
    private static Connection connection = BDDSQL.getConnection();

    /*
     * Méthodes (CRUD)
     */
    public static Roles save(Roles addRoles) {

        Roles newRoles = null;
        try{
            String sql = "INSERT INTO roles(rolesName) VALUE(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,addRoles.getRolesName());

            int nbrRows = preparedStatement.executeUpdate();

            if(nbrRows > 0){
                newRoles = new Roles();
                newRoles.setRolesName(addRoles.getRolesName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newRoles;
    }
    public static boolean isExist(String rolesName) {
        boolean getRoles = false;
        try {
            String sql = "SELECT id FROM roles WHERE rolesName = ?";
            //préparer la requête
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Bind le paramètre
            preparedStatement.setString(1, rolesName);
            //récupérer le resultat de la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            //Vérification du résultat
            while(resultSet.next()){
                getRoles = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return getRoles;
    }
    public static Roles findBy(String rolesName) {
        Roles findRoles = null;
        try {
            String sql = "SELECT id,rolesName FROM roles WHERE rolesName = ?";
            //Préparer la requête
            PreparedStatement prepare = connection.prepareStatement(sql);
            //Bind un paramètre
            prepare.setString(1, rolesName);
            //Exécuter la requête
            ResultSet resultSet = prepare.executeQuery();
            if(resultSet.next()){
                findRoles = new Roles();
                findRoles.setId(resultSet.getInt("id"));
                findRoles.setRolesName(resultSet.getString("rolesName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findRoles;
    }
    public static List<Roles> findAll(){
        List<Roles> allRoles = new ArrayList<>();
        try {
            String sql = "SELECT id, rolesName FROM roles";
            //Préparer la requête
            PreparedStatement prepare = connection.prepareStatement(sql);
            //Exécuter la requête
            ResultSet resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                Roles roles = new Roles();
                roles.setId(resultSet.getInt("id"));
                roles.setRolesName(resultSet.getString("rolesName"));
                allRoles.add(roles);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return allRoles;
    }
}
