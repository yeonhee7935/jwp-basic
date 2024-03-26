package next.dao;
import java.util.List;

import next.model.User;

public class UserDao {


    public void insert(User user) {

        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        new JdbcTemplate() {
        }.executeUpdate("INSERT INTO USERS VALUES (?, ?, ?, ?)", pss);

    }


    public void update(User user) {
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        new JdbcTemplate() {
        }.executeUpdate("Update USERS SET password=?, name=?, email=? WHERE userid=?", pss);
    }

    public User findByUserId(String userId) {
        RowMapper<User> rm = rs -> {
            User user = null;
            while (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
        };
        PreparedStatementSetter pss = pstmt -> pstmt.setString(1, userId);
        return new JdbcTemplate() {
        }.executeQuery("SELECT userId, password, name, email FROM USERS WHERE userid=?", pss, rm);
    }

    public List<User> findAll() {

        RowMapper<User> rm = rs -> {
            User user = null;
            while (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
        };
        PreparedStatementSetter pss = pstmt -> {
        };
        return new JdbcTemplate() {
        }.executeQueryForObject("SELECT userId, password, name, email FROM USERS", pss, rm);
    }


}
