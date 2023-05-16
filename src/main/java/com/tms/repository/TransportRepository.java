package com.tms.repository;

import com.tms.domain.Transport;
import com.tms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport,Integer> {

/*    public JdbcTemplate template;

    @Autowired
    public TransportRepository(JdbcTemplate template) {
        this.template = template;
    }



    public Transport getTransportById(int id) {
        return template.queryForObject("SELECT * FROM l_user_transport WHERE id=?", new TransportMapper(), id);
    }

    public boolean createTransport(Transport transport) {
         int result = template.update("INSERT INTO l_user_transport (id,id_user,type_transport,weight_transport,volume_transport)" +   // добавить в бд(+запрос) new Date((new java.util.Date()).getTime())); МОЖНО ДОБАВИТЬ ДАТУ ДОБАВЛЕНИЯ
                    "VALUES (DEFAULT,?, ?, ?,?)",new Object[]{transport.getUserId(),transport.getTypeTransport(),transport.getWeightTransport(),transport.getVolumeTransport()});
        return result == 1;
    }











    public boolean updateTransport(String typeTransport, int weightTransport, int volumeTransport, int id, int userId) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE l_user_transport SET type_transport=?,volume_transport=?,weight_transport=? where id=? AND id_user=?");
            statement.setString(1, typeTransport);
            statement.setInt(2, weightTransport);
            statement.setInt(3, volumeTransport);
            statement.setInt(4, id);
            statement.setInt(5, userId);
            //statement.setDate(13, new Date((new java.util.Date()).getTime())); //TODO: CHANGE DATE
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean deleteTransport(int id, int userId) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE l_user_transport SET is_deleted=true WHERE id=? AND id_user=?");
            statement.setInt(1, id);
            statement.setInt(2, userId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public List<Transport> getUserTransportById(int userId) {
        List<Transport> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM l_user_transport WHERE id_user=?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Transport transport = new Transport();
                transport.setId(resultSet.getInt("id"));
                transport.setTypeTransport(resultSet.getString("type_transport"));
                transport.setWeightTransport(resultSet.getInt("volume_transport"));
                transport.setVolumeTransport(resultSet.getInt("weight_transport"));
                transport.setUserId(resultSet.getInt("id_user"));
                transport.setDeleted(resultSet.getBoolean("is_deleted"));
                list.add(transport);
            }
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return list;
    }

    public List<Transport> getAllTransports() {
        List<Transport> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM l_user_transport ");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Transport transport = new Transport();
                transport.setId(resultSet.getInt("id"));
                transport.setTypeTransport(resultSet.getString("type_transport"));
                transport.setWeightTransport(resultSet.getInt("volume_transport"));
                transport.setVolumeTransport(resultSet.getInt("weight_transport"));
                transport.setUserId(resultSet.getInt("id_user"));
                transport.setDeleted(resultSet.getBoolean("is_deleted"));
                list.add(transport);
            }
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return list;
    }*/


}
