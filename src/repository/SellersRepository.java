package repository;

import config.DataBaseConnection;
import entity.Sellers;
import exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellersRepository {


    public void save(Sellers sellers) {
        String sql = "insert into sellers(name,username,password) values (?,?,?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sellers.getName());
            statement.setString(2, sellers.getUsername());
            statement.setString(3, sellers.getPassword());
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new DataAccessException("Error saving seller", e);
        }


    }

    public Sellers findById(int id) {
        String sql = "select *from sellers where id=?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Sellers(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password"));

            }
            return null;


        } catch (SQLException e) {
            throw new DataAccessException("Error finding seller", e);
        }

    }

    public Sellers findByUsername(String username) {
        String sql = "select *from sellers where username=?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Sellers(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }
            return null;


        } catch (SQLException e) {
            throw new DataAccessException("Error finding seller by username", e);
        }


    }
}
