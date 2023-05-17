/*
package com.tms.repository;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class FavoriteRepository {

    public boolean add(int userId, int transportId,int cargoId) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root")) {
            if (transportId==0)
            {

                PreparedStatement statement = connection.prepareStatement("INSERT INTO favorite (id,id_user,id_transport,id_cargo)" +
                        "VALUES (DEFAULT, ?, DEFAULT , ?)");
                statement.setInt(1, userId);
                statement.setInt(2, cargoId);

                result = statement.executeUpdate();

            }
            if (cargoId==0)
            {

                PreparedStatement statement = connection.prepareStatement("INSERT INTO favorite (id,id_user,id_transport,id_cargo)" +
                        "VALUES (DEFAULT, ?, ? , DEFAULT)");
                statement.setInt(1, userId);
                statement.setInt(2, transportId);

                result = statement.executeUpdate();

            }
            else {

                PreparedStatement statement = connection.prepareStatement("INSERT INTO favorite (id,id_user,id_transport,id_cargo)" +
                        "VALUES (DEFAULT, ?, ? , ?)");
                statement.setInt(1, userId);
                statement.setInt(2, transportId);
                statement.setInt(3, cargoId);

                result = statement.executeUpdate();

            }
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public ArrayList<Cargo> getCargo(int userId) {
        ArrayList<Cargo> cargoList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root"))
        {
            PreparedStatement statement = connection.prepareStatement(" SELECT l_user_cargo.weight_cargo, l_user_cargo.width_cargo, l_user_cargo.lenght_cargo, l_user_cargo.hight, l_user_cargo.states, l_user_cargo.route, favorite.id_cargo FROM   l_user_cargo JOIN favorite ON favorite.id_cargo=l_user_cargo.id WHERE favorite.id_user=?");
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cargo cargo = new Cargo();
                cargo.setWeightCargo(resultSet.getInt("weight_cargo"));
                cargo.setWidthCargo(resultSet.getInt("width_cargo"));
                cargo.setLenghtCargo(resultSet.getInt("lenght_cargo"));
                cargo.setHight(resultSet.getInt("hight"));
                cargo.setStates(resultSet.getString("states"));
                cargo.setRoute(resultSet.getString("route"));
                cargo.setId(resultSet.getInt("id_cargo"));
                cargoList.add(cargo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cargoList;
    }

    public ArrayList<Transport> getTransport(int id) {
        ArrayList<Transport> transportList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Blak_projectSkills", "postgres", "root"))
        {
            PreparedStatement statement = connection.prepareStatement(" SELECT l_user_transport.type_transport, l_user_transport.volume_transport, l_user_transport.weight_transport, favorite.id_transport FROM   l_user_transport JOIN favorite ON favorite.id_transport=l_user_transport.id WHERE favorite.id_user=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transport transport = new Transport();
                transport.setTypeTransport(resultSet.getString("type_transport"));
                transport.setVolumeTransport(resultSet.getInt("volume_transport"));
                transport.setWeightTransport(resultSet.getInt("weight_transport"));
                transport.setId(resultSet.getInt("id_transport"));
                transportList.add(transport);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return transportList;
    }
}
*/
package com.tms.repository;

import com.tms.domain.FavoritesTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FavoriteRepositoryTransport extends JpaRepository<FavoritesTransport,Integer> {
    ArrayList<FavoritesTransport> findByUserId(int userId);

}

