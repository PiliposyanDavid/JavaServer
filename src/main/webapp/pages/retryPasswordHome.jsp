<%--
  Created by IntelliJ IDEA.
  User: davidpiliposyan
  Date: 2/12/18
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">

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

        .wrapper {
            position: absolute;
            left: 40%;
            top: 35%;
        }

        input {
            min-width: 300px;
        }

        a {
            padding-left: 180px;
        }
    </style>
</head>
<body>

<div class="wrapper">
    <h2>Enter youre email</h2><br>

    <form ACTION="/forgot" method="post">
        <div class="form-group">

            <input type="text" placeholder="Email" required class="form-control" id="email" name="email">
        </div>
        <button type="submit" formmethod="post" class="button">Enter</button>
        <a href="/home">Back</a>
    </form>
</div>

