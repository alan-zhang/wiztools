package org.wiztools.snooplastrequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author subwiz
 */
@WebServlet("/snoop/*")
public class RequestStoreServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute("myRequest", Util.getRequest(req));

        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").include(req, resp);
    }
    
}
