package dao;

import dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoUsersSql implements Dao<User> {

    private Connection connection;

    public DaoUsersSql(Connection connection) {
        this.connection = connection;
    }

    public User getUserToShow(int activeUserId) {
        User result = null;

        String sql = "SELECT * FROM tinderam_users WHERE id NOT IN (\n" +
                "    SELECT checked FROM tinderam_checked WHERE checked = id\n" +
                ") AND id != ? LIMIT 1";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, activeUserId);
            ResultSet rSet = stm.executeQuery();

            if (rSet.next()) {
                String name = rSet.getString("name");
                int id = rSet.getInt("id");
                String surname = rSet.getString("surname");
                String login = rSet.getString("login");
                String password = rSet.getString("password");
                String imgUrl = rSet.getString("imgUrl");
                result = new User(id, login, password, name, surname, imgUrl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public void add(User user) {
        String sql = "INSERT INTO tinderam_users(login, password, name, surname,imgUrl) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getLogin());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getName());
            stm.setString(4, user.getSurname());
            stm.setString(5, user.getImgUrl());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        throw new IllegalStateException("Method is not supplied by this implementation");
    }

    public User getByLogin(User user) {
        User result = null;
        String sql = "SELECT * FROM tinderam_users WHERE login = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getLogin());
            ResultSet rSet = stm.executeQuery();

            if (rSet.next()) {
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                int id = rSet.getInt("id");
                String login = rSet.getString("login");
                String password = rSet.getString("password");
                String imgUrl = rSet.getString("imgUrl");
                result = new User(id, login, password, name, surname, imgUrl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public User get(int id) {
        User user = null;
        String sql = "SELECT tinderam_users.name, tinderam_users.surname,tinderam_users.login,tinderam_users.imgUrl FROM tinderam_users WHERE id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rSet = stm.executeQuery();

            if (rSet.next()) {
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                String login = rSet.getString("login");
                String imgUrl = rSet.getString("imgUrl");
                user = new User(id, login, name, surname, imgUrl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

    @Override
    public List<User> getAll() {
        throw new IllegalStateException("Method is not supplied by this implementation");
    }
}