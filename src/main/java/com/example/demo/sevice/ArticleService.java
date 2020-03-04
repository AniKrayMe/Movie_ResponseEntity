package com.example.demo.sevice;

import com.example.demo.contant.MariaDBConstantForTable;
import com.example.demo.moddel.Article;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class ArticleService {
    public void create(Article article) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "INSERT INTO article(title, description, date) VALUES(?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, article.getTitle());
                preparedStatement.setString(2, article.getDescription());
                preparedStatement.setDate(3, article.getDate());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<Article> findAll() {
        ArrayList<Article> articles = new ArrayList<>();
        Article article = new Article();
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "SELECT * FROM article";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    article.setTitle(resultSet.getString("title"));
                    article.setDescription(resultSet.getString("description"));
                    article.setDate(resultSet.getDate("date"));
                    articles.add(article);
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return articles;
    }


    public Article findById(int id){
        Article article = new Article();
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null){
                String query = "SELECT * FROM article WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    article.setTitle(resultSet.getString("title"));
                    article.setDescription(resultSet.getString("description"));
                    article.setDate(resultSet.getDate("date"));
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return article;
    }

    public void updateById(int id, Article article) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query = "UPDATE article SET title = ?,description = ?,date = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, article.getTitle());
                preparedStatement.setString(2, article.getDescription());
                preparedStatement.setDate(3, article.getDate());
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
                String query = "DELETE FROM article WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
