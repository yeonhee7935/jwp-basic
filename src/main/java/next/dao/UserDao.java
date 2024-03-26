package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public class UserDao {


    public void insert(User user) {

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        new JdbcTemplate() {
        }.executeUpdate("INSERT INTO USERS VALUES (?, ?, ?, ?)", pss);

    }


    public void update(User user) {
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };
        new JdbcTemplate() {
        }.executeUpdate("Update USERS SET password=?, name=?, email=? WHERE userid=?", pss);
    }

    public User findByUserId(String userId) {
        RowMapper rm = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                User user = null;
                while (rs.next()) {
                    user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                }

                return user;
            }
        };
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
        Object user = new JdbcTemplate() {
        }.executeQuery("SELECT userId, password, name, email FROM USERS WHERE userid=?", pss, rm);
        return (User) user;
    }

    public List<User> findAll() {

        RowMapper rm = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                List<User> users = new ArrayList<>();
                if (rs.next()) {
                    User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                    users.add(user);
                }

                return users;
            }
        };
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
            }
        };
        Object users = new JdbcTemplate() {
        }.executeQuery("SELECT userId, password, name, email FROM USERS", pss, rm);
        return (List<User>) users;
    }


}
