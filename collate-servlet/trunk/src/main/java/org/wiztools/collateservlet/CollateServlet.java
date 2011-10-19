package org.wiztools.collateservlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CollateServlet combines multiple filesystem resources into a single resource
 * and serves it.
 * @author subwiz
 */
@WebServlet(name = "CollateServlet", urlPatterns = {"/collate/*"})
public class CollateServlet extends HttpServlet {
    
    private static final String PROP_FILE = "/collate-servlet.properties";
    private final Properties props = new Properties();
    
    private static final String FILES_PREFIX = "collate.servlet.files.";
    private static final String CONTENT_TYPE_PREFIX = "collate.servlet.content-type.";

    @Override
    public void init() throws ServletException {
        try{
            props.load(CollateServlet.class.getResourceAsStream(PROP_FILE));
        }
        catch(IOException ex) {
            throw new ServletException(
                    "Failed to load property file: " + PROP_FILE, ex);
        }
    }
    
    private void write(InputStream is, OutputStream os) throws IOException {
        byte[] buf = new byte[1024*16];
        int readLen = -1;
        while((readLen = is.read(buf))!=-1) {
            os.write(buf, 0, readLen);
        }
        is.close();
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String keySuffix = request.getPathInfo();
        
        if(keySuffix == null || keySuffix.trim().equals("")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No key passed.");
            return;
        }
        
        final String key = FILES_PREFIX + keySuffix;
        final String contentType = CONTENT_TYPE_PREFIX + keySuffix;
        
        final String files = props.getProperty(key);
        
        if(files == null || files.trim().equals("")
                || contentType == null || contentType.trim().equals("")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Key not configured.");
            return;
        }
        
        response.setContentType(contentType);
        response.addHeader("Content-Encoding", "gzip");
        
        BufferedOutputStream out = new BufferedOutputStream(
                new GZIPOutputStream(response.getOutputStream()));
        
        for(final String filePath: files.split("\\s*,\\s*")) {
            File file = new File(filePath);
            write(new FileInputStream(file), out);
        }
        out.flush();
        out.close();
    }
}
