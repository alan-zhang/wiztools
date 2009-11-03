<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WizTools.org SysInfo</title>
    </head>
    <body>
        <h3>System Properties</h3>
        
        <table border="1">
            <tr><th>Key</th><th>Value</th></tr>
            <c:forEach items="${requestScope.sysProperties}" var="item">
                <tr>
                    <td>${item.key}</td><td>${item.value}</td>
                </tr>
            </c:forEach>
        </table>

        <h3>Environment Properties</h3>

        <table border="1">
            <tr><th>Key</th><th>Value</th></tr>
            <c:forEach items="${requestScope.env}" var="item">
                <tr>
                    <td>${item.key}</td><td>${item.value}</td>
                </tr>
            </c:forEach>
        </table>

        <h3>Runtime Values</h3>

        <table border="1">
            <tr><th>Key</th><th>Value</th></tr>
            <tr>
                <td>Number of processors</td><td>${requestScope.processorNum}</td>
            </tr>
            <tr>
                <td>Total memory</td><td>${requestScope.totalMemory}</td>
            </tr>
            <tr>
                <td>Free memory</td><td>${requestScope.freeMemory}</td>
            </tr>
            <tr>
                <td>Heap memory used</td><td>${requestScope.heapUsed}</td>
            </tr>
            <tr>
                <td>Non-heap memory used</td><td>${requestScope.nonHeapUsed}</td>
            </tr>
            <tr>
                <td>Max memory</td><td>${requestScope.maxMemory}</td>
            </tr>
        </table>
        <p align="center"><a href="http://wiztools.org">WizTools.org</a></p>
    </body>
</html>
