<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style type="text/css">
        .button {
            background-color: #555555;;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .wrapper {
            position: absolute;
            left: 40%;
            top: 45%;
        }
    </style>
</head>
<body>
<div class="wrapper">

    <h1><br>

        <%=request.getAttribute("error") %>
    </h1>


    <button   onclick="location.href='/user'" class="button">Yes</button>&nbsp;&nbsp;
    <form ACTION="/user" method="post">

        <button type="submit" class="button">No</button>

    </form>

</div>
</body>
</html>