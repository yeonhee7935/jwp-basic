package next.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controller controller = RequestMapping.getController(request.getRequestURI());

        String viewName = "";
        try {
            viewName = controller.execute(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            String redirectUrl = viewName.substring(DEFAULT_REDIRECT_PREFIX.length());
            response.sendRedirect(redirectUrl);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher(viewName);
            rd.forward(request, response);
        }
    }
}
