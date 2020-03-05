package com.example.demo.sevice;

import com.example.demo.contant.MariaDBConstantForTable;
import com.example.demo.moddel.Movie;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class MovieService {

    public void create(Movie movie) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "INSERT INTO movies(title, genre, country) VALUES(?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getCountry());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<Movie> findAll() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "SELECT * FROM movies";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    movie.setTitle(resultSet.getString("title"));
                    movie.setGenre(resultSet.getString("genre"));
                    movie.setCountry(resultSet.getString("country"));
                    movies.add(movie);
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return movies;
    }

    public Movie findById(int id){
        Movie movie = new Movie();
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null){
                String query = "SELECT * FROM movies WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    movie.setTitle(resultSet.getString("title"));
                    movie.setGenre(resultSet.getString("genre"));
                    movie.setCountry(resultSet.getString("country"));
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return movie;
    }



    public void updateById(int id, Movie movie) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "UPDATE movies SET title = ?,genre = ?,country = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getCountry());
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
                String query = "DELETE FROM movies WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}


