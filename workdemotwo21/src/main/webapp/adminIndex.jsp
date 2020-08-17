<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/8/7
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/adminIndex.js"></script>
    <script type="text/javascript" src="js/adminManageUser.js"></script>
    <script type="text/javascript" src="js/loginOut.js"></script>
    <script>
        var userNameSession= "<%=session.getAttribute("user")%>";
    </script>
    <style>
        .div_book_title{
            text-align: center;
            border: 1px solid #B1D1CE;
            background-color: #F3F3F3;
        }
        .div_left{
            margin: 3px;
            float: left;
            width: 100px;
            border: 1px solid #B1D1CE;
            background-color: #F3F3F3;
            text-align: center;
        }
        .div_left_a{
            color: red;
        }
        .div_right{
            /*width: 700px;*/
            background-color: #F3F3F3;
            text-align: center;
        }
        .tab-title{
            margin: 5px;
        }
        a{text-decoration: none}
        a:hover{text-decoration: none}
        th{
            width: 100px;
            height: 40px;
        }
        table{
            text-align:center;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="div_book_title"><h1>网上书店</h1></div>
<div>
    <!--左侧导航栏-->
    <div class="div_left">
        <p><a href="/adminIndex.jsp" class="div_left_a">图书信息管理</a></p>
        <p><a id="addBookInfo" onclick="addBookInfo()">添加图书信息</a></p>
        <p><a id="updateBookInfo" onclick="updateBookInfo()">更改图书信息</a></p>&nbsp;&nbsp;
        <p><a href="/adminIndex.jsp" class="div_left_a">用户信息管理</a></p>
        <p><a href="/register.jsp">添加用户信息</a></p>
        <p><a id="allUserInfo" onclick="allUserInfo()">查改用户信息</a></p>&nbsp;&nbsp;
        <p><a href="/adminIndex.jsp" class="div_left_a">用户退出</a></p>
        <p><a id="loginOut" onclick="loginOut()">退出</a></p>
    </div>
    <!--右侧，详细信息-->
    <div class="div_right" id="div_right"></div>
</div>
<div class="div_update" id="div_update"></div>
</body>
</html>
