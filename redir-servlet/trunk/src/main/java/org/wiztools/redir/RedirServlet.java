package org.wiztools.redir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author agile
 */
public class RedirServlet extends HttpServlet {
    
    private static final Logger LOG = Logger.getLogger(RedirServlet.class.getName());
    
    private static final String RE_VALID = "^[a-zA-Z0-9\\-]{1,10}$";
    
    private String configFolder;
    
    /* Class to encapsulate error message and HTTP status code */
    class ErrorMsg{
        final String message;
        final int code;
        public ErrorMsg(final String message, final int code){
            this.message = message;
            this.code = code;
        }
    }
    
    private void showError(HttpServletRequest request, 
            HttpServletResponse response, ErrorMsg errMsg) throws ServletException, IOException{
        LOG.warning("HTTP Code: " + errMsg.code + "\n\t" + errMsg.message);
        request.setAttribute("errMsg", errMsg.message);
        response.sendError(errMsg.code, errMsg.message);
    }
    
    @Override
    public void init(){
        ResourceBundle rb = ResourceBundle.getBundle("folder");
        configFolder = rb.getString("configFolder");
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String pathInfo = request.getPathInfo();
        if(pathInfo == null){
            showError(request, response, new ErrorMsg("No path given!", 404));
            return;
        }
        // pathInfo always starts with /. Remove that:
        pathInfo = pathInfo.substring(1);
        if(pathInfo.matches(RE_VALID)){
            File f = new File(configFolder + File.separatorChar + pathInfo + ".url");
            if(f.exists()){
                BufferedReader br = null;
                try{
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(f), Charset.forName("UTF-8")));
                    String url = br.readLine();
                    response.sendRedirect(url);
                }
                catch(IOException ex){
                    LOG.severe(ex.getStackTrace().toString());
                    showError(request,
                            response,
                            new ErrorMsg("Could not read the file DB: " + f.getName(), 500));
                }
                finally{
                    if(br != null){
                        br.close();
                    }
                }
            }
            else{
                showError(request, response,
                        new ErrorMsg("Redirect info for '/" + pathInfo + "' not available!", 404));
            }
        }
        else{
            showError(request, response,
                    new ErrorMsg("Path should match this RE: " + RE_VALID, 400));
        }
    }
}
