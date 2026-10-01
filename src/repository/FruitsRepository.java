package repository;

import config.DataBaseConnection;
import entity.Customers;
import entity.Fruits;
import exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FruitsRepository {
    public void save(Fruits fruits) {
        String sql = "insert into fruits(name,description,stock,price) values (?,?,?,?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fruits.getName());
            statement.setString(2, fruits.getDescription());
            statement.setDouble(3, fruits.getStock());
            statement.setDouble(4, fruits.getPrice());

            statement.executeUpdate();


        } catch (SQLException e) {
            throw new DataAccessException("Error saving Fruit", e);
        }


    }

    public Fruits findById(int id) {
        String sql = "select *from fruits where id=?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Fruits(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("stock"),
                        resultSet.getDouble("price"));
            }
            return null;


        } catch (SQLException e) {
            throw new DataAccessException("Error finding fruit", e);
        }

    }

    public List<Fruits> findAll() {
        String sql = "select *from fruits";
        List<Fruits> fruits = new ArrayList<>();
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                fruits.add(new Fruits(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("stock"),
                        resultSet.getDouble("price")));

            }
            return fruits;


        } catch (SQLException e) {
            throw new DataAccessException("Error finding fruits", e);
        }


    }

    public void update(Fruits fruits) {
        String sql = "Update fruits Set name=?, description= ?, stock=?,price=? where id=?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fruits.getName());
            statement.setString(2, fruits.getDescription());
            statement.setDouble(3, fruits.getStock());
            statement.setDouble(4, fruits.getPrice());
            statement.setInt(5, fruits.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            throw new DataAccessException("Error updating Fruit", e);
        }
    }

    public void delete(int id) {
        String sql = "delete from fruits  where id=?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();


        } catch (SQLException e) {
            throw new DataAccessException("Error deleting Fruit", e);
        }
    }


}
