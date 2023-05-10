package com.tms.repository;

import com.tms.domain.Cargo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CargoRepository {

    public Cargo getCargoById(int id) {
        Cargo cargo = new Cargo();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM l_user_cargo WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            cargo.setId(resultSet.getInt("id"));
            cargo.setWeightCargo(resultSet.getInt("weight_cargo"));
            cargo.setWidthCargo(resultSet.getInt("width_cargo"));
            cargo.setLenghtCargo(resultSet.getInt("lenght_cargo"));
            cargo.setHight(resultSet.getInt("hight"));
            cargo.setStates(resultSet.getString("states"));
            cargo.setUserId(resultSet.getInt("id_user"));
            cargo.setRoute(resultSet.getString("route"));
            cargo.setDeleted(resultSet.getBoolean("is_deleted"));

        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return cargo;
    }

    public boolean createCargo(int userId, int weightCargo, int widthCargo, int lenghtCargo, int hight, String states, String route) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO l_user_cargo (id,id_user,weight_cargo,width_cargo,lenght_cargo,hight,states,route)" +
                    "VALUES (DEFAULT, ?, ?,?,?,?,?,?)");
            statement.setInt(1, userId);
            statement.setInt(2, weightCargo);
            statement.setInt(3, widthCargo);
            statement.setInt(4, lenghtCargo);
            statement.setInt(5, hight);
            statement.setString(6, states);
            statement.setString(7, route);
            /* statement.setDate(16, new Date((new java.util.Date()).getTime())); МОЖНО ДОБАВИТЬ ДАТУ ДОБАВЛЕНИЯ*/
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean updateCargo(int weightCargo, int widthCargo, int lenghtCargo, int hight, String states, String route, int id, int userId) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE l_user_cargo SET weight_cargo=?,width_cargo=?,lenght_cargo=?,hight=?,states=?,route=? where id=? AND id_user=?");
            statement.setInt(1, weightCargo);
            statement.setInt(2, widthCargo);
            statement.setInt(3, lenghtCargo);
            statement.setInt(4, hight);
            statement.setString(5, states);
            statement.setString(6, route);
            statement.setInt(7, id);
            statement.setInt(8, userId);
            /*statement.setDate(13, new Date((new java.util.Date()).getTime())); //TODO: CHANGE DATE*/
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean deleteCargo(int id, int userId) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE l_user_cargo SET is_deleted=true WHERE id=? AND id_user=?");
            statement.setInt(1, id);
            statement.setInt(2, userId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public List<Cargo> getUserCargoById(int userId) {
        java.util.List<Cargo> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM l_user_cargo WHERE id_user=?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultSet.getInt("id"));
                cargo.setWeightCargo(resultSet.getInt("weight_cargo"));
                cargo.setWidthCargo(resultSet.getInt("width_cargo"));
                cargo.setLenghtCargo(resultSet.getInt("lenght_cargo"));
                cargo.setHight(resultSet.getInt("hight"));
                cargo.setStates(resultSet.getString("states"));
                cargo.setUserId(resultSet.getInt("id_user"));
                cargo.setRoute(resultSet.getString("route"));
                cargo.setDeleted(resultSet.getBoolean("is_deleted"));
                list.add(cargo);
            }
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return list;
    }

    public List<Cargo> getAllCargos() {
        java.util.List<Cargo> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM l_user_cargo");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultSet.getInt("id"));
                cargo.setWeightCargo(resultSet.getInt("weight_cargo"));
                cargo.setWidthCargo(resultSet.getInt("width_cargo"));
                cargo.setLenghtCargo(resultSet.getInt("lenght_cargo"));
                cargo.setHight(resultSet.getInt("hight"));
                cargo.setStates(resultSet.getString("states"));
                cargo.setUserId(resultSet.getInt("id_user"));
                cargo.setRoute(resultSet.getString("route"));
                cargo.setDeleted(resultSet.getBoolean("is_deleted"));
                list.add(cargo);
            }
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return list;
    }

}
