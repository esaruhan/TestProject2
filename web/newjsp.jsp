<%-- 
    Document   : newjsp
    Created on : 08.Mar.2013, 15:46:54
    Author     : LifeBook
--%>

<%@page import="vodafone.tarife_oner_islemler.Singleton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h1>Hello World!</h1>
    <a href="VodafoneRaporlar\ThinkingInJava .pdf"> Test </a>
    <% out.write("size"+Singleton.getInstance().getTarifeler().size());  %>
</body>
</html>
