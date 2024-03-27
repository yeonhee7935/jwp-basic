package next.controller.qna;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuestionDetailController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("id");
        QuestionDao dao = new QuestionDao();
        Question question = dao.findById(questionId);
        req.setAttribute("question", question);

        AnswerDao answerDao = new AnswerDao();
        List<Answer> answers = answerDao.findByQuestionId(questionId);
        req.setAttribute("answers", answers);
        return "/qna/show.jsp";
    }
}
