<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String url = "devtools://devtools/bundled/js_app.html?ws=" + 
    request.getServerName() + ":" + request.getServerPort() + 
    request.getContextPath() + "/debug/";
    
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smeup Debuggee</title>
    </head>
    <body>
        <h2>com.smeup.debuggee.helloworld.HelloWorldDebuggee</h2>
        <code><%=url%>helloworld/source.txt</code>
        <h2>com.smeup.debuggee.simple.SimpleDebuggeeImpl</h2>
        <code><%=url%>simple/source.txt</code>
    </body>
</html>
