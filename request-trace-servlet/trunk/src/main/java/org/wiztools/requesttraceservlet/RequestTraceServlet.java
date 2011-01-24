package org.wiztools.requesttraceservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author subhash
 */
public class RequestTraceServlet extends HttpServlet {
   
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("==Request TraceServlet==");

        out.println("\n===Method===");
        out.println("\t" + req.getMethod());

        out.println("\n===Headers===");
        Enumeration eHeaders = req.getHeaderNames();
        while(eHeaders.hasMoreElements()){
            final String headerName = (String)eHeaders.nextElement();
            Enumeration eValues = req.getHeaders(headerName);
            while(eValues.hasMoreElements()) {
                String headerValue = (String) eValues.nextElement();
                headerValue = headerValue.replaceAll("\n", "\n\t");
                out.println("\t" + headerName + ": " + headerValue);
            }
        }
        
        out.println("\n===Request URL===");
        out.println("\t" + req.getRequestURL());

        out.println("\n===Path Info===");
        out.println("\t" + req.getPathInfo());

        out.println("\n===Query String===");
        out.println("\t" + req.getQueryString());

        out.println("\n===Parameters===");
        Enumeration eParams = req.getParameterNames();
        while(eParams.hasMoreElements()){
            String paramName = (String)eParams.nextElement();
            String[] paramValues = req.getParameterValues(paramName);

            for(String paramValue: paramValues) {
                paramValue = paramValue.replaceAll("\n", "\n\t");

                out.println("\t~Parameter Name: " + paramName);
                out.println("\t~Parameter Value:");
                out.println("\t" + paramValue);
            }
        }

        out.println("\n===Body===");
        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String str = null;
            while((str = br.readLine())!=null){
                out.println("\t" + str);
            }
        }
        finally{
            if(br != null)
                br.close();
        }

        out.flush();
        out.close();
    }

}
