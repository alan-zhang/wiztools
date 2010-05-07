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
        final MyRequest myRequest = Util.getRequest(req);
        getServletContext().setAttribute("myRequest", myRequest);

        System.out.println();
        System.out.println(myRequest);
        System.out.println();

        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/jsp/stored.jsp").include(req, resp);
    }
    
}
