package org.wiztools.servletpathinfo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author subwiz
 */
public class PathServlet extends HttpServlet {
    
    private void addData(PrintWriter out, String name, String value) {
        out.println("<tr><td>" + name + "</td><td>" + value + "</td></tr>");
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PathServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PathServlet at " + request.getContextPath() + "</h1>");
            
            out.println("<table border='1' cellpadding='5'><tr><th>Servlet Variable</th><th>Value</th></tr>");
            
            final String contextPath = request.getContextPath();
            addData(out, "contextPath", contextPath);
            
            final String localAddr = request.getLocalAddr();
            addData(out, "localAddr", localAddr);
            
            final String localName = request.getLocalName();
            addData(out, "localName", localName);
            
            final String localPort = String.valueOf(request.getLocalPort());
            addData(out, "localPort", localPort);
            
            final String pathInfo = request.getPathInfo();
            addData(out, "pathInfo", pathInfo);
            
            final String pathTranslated = request.getPathTranslated();
            addData(out, "pathTranslated", pathTranslated);
            
            final String protocol = request.getProtocol();
            addData(out, "protocol", protocol);
            
            final String queryString = request.getQueryString();
            addData(out, "queryString", queryString);
            
            final String remoteAddr = request.getRemoteAddr();
            addData(out, "remoteAddr", remoteAddr);
            
            final String remoteHost = request.getRemoteHost();
            addData(out, "remoteHost", remoteHost);
            
            final String remotePort = String.valueOf(request.getRemotePort());
            addData(out, "remotePort", remotePort);
            
            final String requestURI = request.getRequestURI();
            addData(out, "requestURI", requestURI);
            
            final String requestURL = request.getRequestURL().toString();
            addData(out, "requestURL", requestURL);
            
            final String serverName = request.getServerName();
            addData(out, "serverName", serverName);
            
            final String serverPort = String.valueOf(request.getServerPort());
            addData(out, "serverPort", serverPort);
            
            final String servletPath = request.getServletPath();
            addData(out, "servletPath", servletPath);
            
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
