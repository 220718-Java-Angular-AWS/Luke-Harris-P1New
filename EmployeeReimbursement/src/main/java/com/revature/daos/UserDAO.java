package com.revature.daos;

import com.revature.services.ConnectionManager;
import com.revature.pojos.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements DatasourceCRUD<User> {
    Connection connection;

    public UserDAO() {
        this.connection = ConnectionManager.connectionManager.getConnection();
    }

    @Override
    public void create(User user) {
        try {
            String sql = "INSERT INTO users (first_name, last_name, user_pass, user_admin, email_address) VALUES (?, ?, ?, 'false', ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUserPass());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();

            if (results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email_address"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User logIn(String email, String password) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE email_address = ? AND user_pass = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2,password);
            ResultSet results = pstmt.executeQuery();

            if (results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email_address"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()) {
                User user = new User();
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email_address"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void update(User user) {
        try {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, user_pass = ?, user_admin = ?, email_address = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUserPass());
            pstmt.setBoolean(4, user.isAdmin());
            pstmt.setString(5, user.getEmail());
            pstmt.setInt(6, user.getUserId());
            pstmt.executeUpdate();
            ResultSet id = pstmt.getGeneratedKeys();
            if(id.next()) {
                Integer key = id.getInt("user_id");
                System.out.println("ID: " + key);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}