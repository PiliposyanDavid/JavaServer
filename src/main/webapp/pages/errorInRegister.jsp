<%--
  Created by IntelliJ IDEA.
  User: davidpiliposyan
  Date: 2/1/18
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <style type="text/css">


        .wrapper {
            position: absolute;
            left: 40%;
            top: 35%;
        }

        .button {
            background-color: #555555;;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <h1><br>

            <%=request.getAttribute("error") %>
    </h1>


        <form ACTION="/register" method="get">

            <button type="submit" class="button">Back</button>
        </form>

</div>
</body>
</html>

