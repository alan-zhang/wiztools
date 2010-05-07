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
@WebServlet("/index.html")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/WEB-INF/jsp/index.jsp";
        if(getServletContext().getAttribute("myRequest") == null){
            path = "/WEB-INF/jsp/new.jsp";
        }

        resp.setContentType("text/html");
        req.getRequestDispatcher(path).include(req, resp);
    }

}
