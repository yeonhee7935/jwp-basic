package next.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/users/create", new CreateUserController());
        controllers.put("/users/login", new LoginController());
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users/list", new ListUserController());
        controllers.put("/users/logout", new LogoutController());
        controllers.put("/users/profile", new ProfileController());
        controllers.put("/users/updateForm", new UserUpdateFormController());
        controllers.put("/users/update", new UpdateUserController());

    }

    public static Controller getController(String pattern) {
        return controllers.get(pattern);
    }
}
