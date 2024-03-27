package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;
import next.model.Question;
import next.model.User;

public class AnswerDao {
    public void insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO ANSWERS(writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
    }

    public Answer findById(String answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM ANSWERS WHERE answerId=?";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getLong("questionId")
                );

            }
        };

        return jdbcTemplate.queryForObject(sql, rm, answerId);
    }

    public List<Answer> findByQuestionId(String questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM ANSWERS WHERE questionId=?";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate"),
                        rs.getLong("questionId")
                );
            }
        };

        return jdbcTemplate.query(sql, rm, questionId);
    }

    public List<Answer> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT * FROM ANSWERS";

        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer();
            }
        };

        return jdbcTemplate.query(sql, rm);
    }

    public void update(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE ANSWERS set contents = ?";
        jdbcTemplate.update(sql, answer.getContents());
    }
}