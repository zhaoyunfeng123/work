<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/8/7
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
<div style="text-align: center">
    <h2>用户登录</h2>
    <p>用户名：<input type="text" id="userName" name="userName"></p>
    <p>密码：<input type="password" id="password" name="password"></p>
    <p>
        <input type="radio" id="adminLogin" name="userType" checked="checked" value="管理员"><label for="adminLogin">管理员</label>&nbsp;&nbsp;&nbsp;
        <input type="radio" id="userLogin" name="userType" value="用户"><label for="userLogin">普通用户</label>
    </p>
    <p>
        <input type="button" id="loginSubmit" name="submit" value="登录" onclick="login()"\>
        <input type="button" id="registerSubmit" name="submit" value="注册" onclick="goRegister()"\>
    </p>
</div>
</body>
</html>
