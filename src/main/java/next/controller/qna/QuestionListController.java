package next.controller.qna;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuestionListController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao dao = new QuestionDao();
        List<Question> questions = dao.findAll();
        // TODO: REST API  방식 구현하기
        return "/qna/show";
    }
}
