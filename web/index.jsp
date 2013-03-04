<%-- 
    Document   : index
    Created on : 02.Mar.2013, 19:38:53
    Author     : LifeBook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <form action="RaporServlet" method="post" enctype="multipart/form-data">
    <label for="file">Filename:</label>
    <input type="file" name="file" id="file"><br>
    <input type="submit" name="submit" value="Submit">
    </form>
    
</body>
</html>
