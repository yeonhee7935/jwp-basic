package next.controller.qna;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class QuestionCreateController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        QuestionDao dao = new QuestionDao();
        dao.insert(new Question(writer, title, contents, createdDate, 0));
        return "redirect:/";
    }
}
