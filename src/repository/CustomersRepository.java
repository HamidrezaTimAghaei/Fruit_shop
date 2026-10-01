package repository;

import config.DataBaseConnection;
import entity.Customers;
import entity.Sellers;
import exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersRepository {
    public void save(Customers customers) {
        String sql = "insert into customers(name,phone,address,username,password) values (?,?,?,?,?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customers.getName());
            statement.setString(2, customers.getPhone());
            statement.setString(3, customers.getAddress());
            statement.setString(4, customers.getUsername());
            statement.setString(5, customers.getPassword());
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new DataAccessException("Error saving customer", e);
        }


    }

    public Customers findById(int id) {
        String sql = "select *from sellers where id=?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               return new Customers(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("phone"),
                resultSet.getString("address"),
                resultSet.getString("username"),
                resultSet.getString("password"));
            }
            return null;


        } catch (SQLException e) {
            throw new DataAccessException("Error finding customer", e);
        }

    }

    public Customers findByUsername(String username) {
        String sql = "select *from customers where username=?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Customers(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));

            }
            return null;


        } catch (SQLException e) {
            throw new DataAccessException("Error finding customer by username", e);
        }


    }
}
