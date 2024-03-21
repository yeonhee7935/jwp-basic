package next.web;

import core.db.DataBase;
import lombok.extern.slf4j.Slf4j;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        HttpSession session = req.getSession();
        Object userObject = session.getAttribute("user");
        // 로그인하지 않은 경우
        if (userObject == null) {
            req.setAttribute("message", "로그인 후 이용할 수 있습니다.");
            RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
            rd.forward(req, resp);
            return;
        }
        // 자신의 정보인 경우
        req.setAttribute("user", DataBase.findUserById(userId));
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 사용자 입력정보 추출
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        HttpSession session = req.getSession();
        Object userObject = session.getAttribute("user");
        // 로그인하지 않은 경우
        if (userObject == null) {
            req.setAttribute("message", "로그인 후 이용할 수 있습니다.");
            RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
            rd.forward(req, resp);
            return;
        }
        User currentUser = (User) userObject;
        // 자신의 정보가 아닌 경우
        if (!Objects.equals(currentUser.getUserId(), userId)) {
            resp.sendRedirect("/user/list");
            return;
        }

        // 개인정보 수정
        User user = new User(userId, password, name, email);
        log.debug(userId+email+name+password);
        DataBase.updateUser(user);
        resp.sendRedirect("/user/list");
    }
}
