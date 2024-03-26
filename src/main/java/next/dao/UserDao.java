package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {


    public void insert(User user) throws SQLException {
        new JdbcTemplate() {

            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return null;
            }

        }.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");

    }


    public void update(User user) throws SQLException {
        new JdbcTemplate() {

            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return null;
            }

        }.update("Update USERS SET password=?, name=?, email=? WHERE userid=?");
    }

    public User findByUserId(String userId) throws SQLException {
        Object user = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                User user = null;
                while (rs.next()) {
                    user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                }

                return user;
            }
        }.select("SELECT userId, password, name, email FROM USERS WHERE userid=?");
        return (User) user;
    }

    public List<User> findAll() throws SQLException {
        Object users = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                List<User> users = new ArrayList<>();
                if (rs.next()) {
                    User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                    users.add(user);
                }

                return users;
            }
        }.select("SELECT userId, password, name, email FROM USERS");
        return (List<User>) users;
    }


}
