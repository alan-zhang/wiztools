package org.wiztools.snooplastrequest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.wiztools.commons.Charsets;
import org.wiztools.commons.MultiValueMap;
import org.wiztools.commons.MultiValueMapLinkedHashSet;
import org.wiztools.commons.StreamUtil;

/**
 *
 * @author subwiz
 */
class Util {
    static MyRequest getRequest(HttpServletRequest req) throws IOException {
        MyRequest r = new MyRequest();

        r.setPathInfo(req.getPathInfo());
        r.setQueryString(req.getQueryString());
        r.setMethod(req.getMethod());

        // Body
        final Charset reqEncoding = req.getCharacterEncoding()!=null? 
            Charset.forName(req.getCharacterEncoding()): Charsets.UTF_8;
        r.setBody(StreamUtil.inputStream2String(
                req.getInputStream(), reqEncoding));

        // Headers
        MultiValueMap<String, String> headers = new MultiValueMapLinkedHashSet<String, String>();
        Enumeration eHeaders = req.getHeaderNames();
        while(eHeaders.hasMoreElements()){
            final String headerName = (String)eHeaders.nextElement();
            final Enumeration eHeaderValues = req.getHeaders(headerName);
            while(eHeaderValues.hasMoreElements()){
                final String headerValue = (String)eHeaderValues.nextElement();
                headers.put(headerName, headerValue);
            }
        }
        r.setHeaders(headers);

        // Parameters
        MultiValueMap<String, String> parameters = new MultiValueMapLinkedHashSet<String, String>();
        Enumeration eParams = req.getParameterNames();
        while(eParams.hasMoreElements()){
            final String paramName = (String)eParams.nextElement();
            final String[] paramValues = req.getParameterValues(paramName);
            for(final String paramValue: paramValues){
                parameters.put(paramName, paramValue);
            }
        }
        r.setParameters(parameters);

        return r;
    }
}
