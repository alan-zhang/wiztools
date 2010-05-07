<%@page contentType="text/html" pageEncoding="UTF-8" import="org.wiztools.snooplastrequest.MyRequest,
        org.wiztools.commons.MultiValueMap"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
MyRequest myRequest = (MyRequest)application.getAttribute("myRequest");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WizTools.org: Snoop Last Request</title>
    </head>
    <body>
        <p align="center"><a href="clear.html">Clear</a></p>
        <h3>Last Request Snoop:</h3>

        <table border="1">
            <tr>
                <td>Method:</td>
                <td>${myRequest.method}</td>
            </tr>
            <tr>
                <td>Path Info:</td>
                <td>${myRequest.pathInfo}</td>
            </tr>
            <tr>
                <td>Query String:</td>
                <td>${myRequest.queryString}</td>
            </tr>
        </table>

        <h4>Headers</h4>

        <table border="1">
            <%
            MultiValueMap<String, String> headers = myRequest.getHeaders();
            for(final String key: headers.keySet()){
                request.setAttribute("key", key);
                for(final String value: headers.get(key)){
                    request.setAttribute("value", value);
            %>
            <tr>
                <td><c:out escapeXml="true" value="${key}"/></td>
                <td><c:out escapeXml="true" value="${value}"/></td>
            </tr>
            <%
                }
            }
            %>
        </table>

        <h4>Parameters</h4>

        <table border="1">
            <%
            MultiValueMap<String, String> parameters = myRequest.getParameters();
            for(final String key: parameters.keySet()){
                request.setAttribute("key", key);
                for(final String value: parameters.get(key)){
                    request.setAttribute("value", value);
            %>
            <tr>
                <td><c:out escapeXml="true" value="${key}"/></td>
                <td><c:out escapeXml="true" value="${value}"/></td>
            </tr>
            <%
                }
            }
            %>
        </table>

        <h4>Body</h4>

<pre>
<c:out escapeXml="true" value="${myRequest.body}"/>
</pre>

        <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
