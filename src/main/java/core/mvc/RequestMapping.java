package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.*;
import next.controller.qna.QuestionCreateController;
import next.controller.qna.QuestionDetailController;
import next.controller.qna.QuestionListController;
import next.controller.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private Map<String, Controller> mappings = new HashMap<>();

    void initMapping() {
        mappings.put("/", new HomeController());
        mappings.put("/users/form", new ForwardController("/user/form.jsp"));
        mappings.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        mappings.put("/qna/form", new ForwardController("/qna/form.jsp"));
        mappings.put("/qna/detail", new QuestionDetailController());
        mappings.put("/api/qna/questions/list", new QuestionListController());
        mappings.put("/qna/questions/detail", new QuestionDetailController());
        mappings.put("/qna/questions/create", new QuestionCreateController());
        mappings.put("/users", new UserListController());
        mappings.put("/users/login", new LoginController());
        mappings.put("/users/profile", new ProfileController());
        mappings.put("/users/logout", new LogoutController());
        mappings.put("/users/create", new UserCreateController());
        mappings.put("/users/updateForm", new UserUpdateFormController());
        mappings.put("/users/update", new UserUpdateController());

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String url) {
        return mappings.get(url);
    }

    void put(String url, Controller controller) {
        mappings.put(url, controller);
    }
}
