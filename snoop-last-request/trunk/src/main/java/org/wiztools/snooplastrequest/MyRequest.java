package org.wiztools.snooplastrequest;

import java.util.Collection;
import org.wiztools.commons.MultiValueMap;

/**
 *
 * @author subwiz
 */
public class MyRequest {
    private String method;
    private String pathInfo;
    private String queryString;
    private String body;

    private MultiValueMap<String, String> headers;
    private MultiValueMap<String, String> parameters;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MultiValueMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(MultiValueMap<String, String> headers) {
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MultiValueMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(MultiValueMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

}
