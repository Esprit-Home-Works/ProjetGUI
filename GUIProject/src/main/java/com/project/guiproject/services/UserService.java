package com.project.guiproject.services;

import com.project.guiproject.models.User;
import com.project.guiproject.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserService implements IService<User> {

    private final Connection connection;

    public UserService() {
        connection = MyDataBase.getInstace().getConnection();
    }

    @Override
    public void add(User User) throws SQLException {
        String req = "INSERT INTO user (id,username,password,email) VALUES ('"
                + User.getId() + "', '" + User.getUsername() + "', '" + User.getPassword() + "', '"
                + User.getEmail() + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(req);
    }

    @Override
    public void update(User User) throws SQLException {
        String req = "UPDATE user SET id = ?,username = ?, password = ? ,email = ? WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(req);
        PS.setInt(1, User.getId());
        PS.setString(2, User.getUsername());
        PS.setString(3, User.getPassword());
        PS.setString(4, User.getEmail());
        PS.setInt(5, User.getId());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM user WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(req);
        try {
            PS.setInt(1, id);
            PS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        String query = "SELECT * FROM user WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(query);
        PS.setInt(1, id);
        PS.executeQuery();
        ResultSet RS = PS.getResultSet();
        return RS.next()
                ? new User(RS.getInt("id"), RS.getString("username"), RS.getString("password"), RS.getString("email"))
                : null;

    }

    @Override
    public List<User> get() throws SQLException {
        List<User> User = new ArrayList<>();
        String query = "SELECT * FROM user";
        PreparedStatement PS = connection.prepareStatement(query);
        PS.executeQuery();
        ResultSet RS = PS.getResultSet();
        while (RS.next()) {
            User.add(new User(RS.getInt("id"), RS.getString("username"), RS.getString("password"),
                    RS.getString("email")));
        }
        return User;
    }

    // Login and Signup Methods
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void signup(String username, String password, String email) throws SQLException {
        User User = new User(username, password, email);
        add(User);
    }

    public User registerUser(String testUser, String password123, String mail) {
        return null;
    }
}

