package org.wiztools.requesttraceservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

        out.println("\n====Server Info===");
        out.println("\tLocal Address: " + req.getLocalAddr());
        out.println("\tLocal Name: " + req.getLocalName());
        out.println("\tLocal Port: " + req.getLocalPort());
        out.println("\tServer Port: " + req.getServerPort());

        out.println("\n===Remote===");
        out.println("\tRemote Address: " + req.getRemoteAddr());
        out.println("\tRemote Host: " + req.getRemoteHost());
        out.println("\tRemote Port: " + req.getRemotePort());
        out.println("\tRemote User: " + req.getRemoteUser());
        out.println("\tRequested SessionId: " + req.getRequestedSessionId());

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
        
        if(req.getCookies() != null) {
            out.println("\n===Cookies===");
            for(Cookie cookie: req.getCookies()) {
                out.println("\t" + cookie.getName());
                out.println("\t\tComment: " + cookie.getComment());
                out.println("\t\tDomain: " + cookie.getDomain());
                out.println("\t\tPath: " + cookie.getPath());
                out.println("\t\tMax-age: " + cookie.getMaxAge());
                out.println("\t\tSecure: " + cookie.getSecure());
                out.println("\t\tVersion: " + cookie.getVersion());
                out.println("\t\tValue: " + cookie.getValue());
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
