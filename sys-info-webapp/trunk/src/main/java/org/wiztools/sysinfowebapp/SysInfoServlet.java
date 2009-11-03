package org.wiztools.sysinfowebapp;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
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
        final long totalMemory = r.totalMemory() / (1000L*1024L);

        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage u = memBean.getHeapMemoryUsage();
        final long heapUsed = u.getUsed() / (1000L*1024L);
        u = memBean.getNonHeapMemoryUsage();
        final long nonHeapUsed = u.getUsed() / (1000L*1024L);
        
        final int processorNum = r.availableProcessors();
        
        // Set the values as request attributes:
        req.setAttribute("sysProperties", sysProperties);
        req.setAttribute("env", env);

        req.setAttribute("maxMemory", maxMemory);
        req.setAttribute("freeMemory", freeMemory);
        req.setAttribute("totalMemory", totalMemory);
        req.setAttribute("heapUsed", heapUsed);
        req.setAttribute("nonHeapUsed", nonHeapUsed);
        req.setAttribute("processorNum", processorNum);

        // Forward to JSP:
        req.getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(req, resp);
    }
    
}
