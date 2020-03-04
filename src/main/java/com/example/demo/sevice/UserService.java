package com.example.demo.sevice;

import com.example.demo.contant.MariaDBConstantForTable;
import com.example.demo.moddel.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class UserService {

    public void create(User user) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "INSERT INTO user(name, surname, password) VALUES(?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getBCryptPassword());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "SELECT * FROM user";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setPassword(resultSet.getString("password"));
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return users;
    }

    public User findById(int id){
        User user = new User();
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null){
                String query = "SELECT * FROM user WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return user;
    }



    public void updateById(int id, User user) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "UPDATE user SET name = ?,surname = ?,password = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getBCryptPassword());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void deleteById(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "DELETE FROM user WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}


