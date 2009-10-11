package org.wiztools.sysinfowebapp;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author subwiz
 */
public class SysInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final Map<String, String> sysProperties = new TreeMap(System.getProperties());
        final Map<String, String> env = new TreeMap(System.getenv());

        Runtime r = Runtime.getRuntime();
        // Display in MB:
        final long maxMemory = r.maxMemory() / (1000L*1024L);
        final long freeMemory = r.freeMemory() / (1000L*1024L);
        final int processorNum = r.availableProcessors();
        
        // Set the values as request attributes:
        req.setAttribute("sysProperties", sysProperties);
        req.setAttribute("env", env);

        req.setAttribute("maxMemory", maxMemory);
        req.setAttribute("freeMemory", freeMemory);
        req.setAttribute("processorNum", processorNum);

        // Forward to JSP:
        req.getRequestDispatcher("/view.jsp").forward(req, resp);
    }
    
}
