<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/8/8
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/register.js"></script>
    <style>
        .div_left {
            /*width: 100px;*/
            border: 1px solid #B1D1CE;
            background-color: #F3F3F3;
            /*text-align: center;*/
        }

        .div_table {
            align-content: center;
        }

        a {
            text-decoration: none
        }

        a:hover {
            text-decoration: none
        }

        table {
            text-align: center;
            height: 30px;
        }

        td {
            width: 180px;
        }

        .submitData {
            width: 200px;
        }
    </style>
</head>
<body onload="createCode()">
<div class="div_left">
    <h2>欢迎注册</h2>
    <div class="div_table">
        <form id="form" method="post">
            <table>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" id="userName" name="userName"></td>
                </tr>
                <tr>
                    <td>输入密码</td>
                    <td><input type="password" id="password" name="password"></td>
                </tr>
<%--                <tr>--%>
<%--                    <td>密码强度</td>--%>
<%--                    <td>//没做</td>--%>
<%--                </tr>--%>
                <tr>
                    <td>确认密码</td>
                    <td><input type="password" id="confirmPassword" name="confirmPassword"></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" id="fullName" name="fullName"></td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td><input type="text" id="tel" name="tel"></td>
                </tr>
                <tr>
                    <td>住址</td>
                    <td><input type="text" id="address" name="address"></td>
                </tr>
                <tr>
                    <td>所在城市</td>
                    <td><input type="text" id="city" name="city"></td>
                </tr>
                <tr>
                    <td>国家</td>
                    <td><select id="country" name="country">
                        <option value="China">China</option>
                        <option value="America">America</option>
                        <option value="England">England</option>
                    </select></td>
                </tr>
                <tr>
                    <td>E-mail</td>
                    <td><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td>兴趣爱好</td>
                    <td><input type="checkbox" id="javaDevelopment" name="hobby" value="Java开发">Java开发
                        <input type="checkbox" id="webDevelopment" name="hobby" value="Web前端开发">Web前端开发
                        <input type="checkbox" id="androidDevelopment" name="hobby" value="Android开发">Android开发
                        <input type="checkbox" id="uiDesign" name="hobby" value="UI设计">UI设计
                        <input type="checkbox" id="systemOperation" name="hobby" value="系统运维">系统运维
                    </td>
                </tr>
                <tr>
                    <td>密保问题</td>
                    <td><select id="problem" name="problem">
                        <option value="phoneNumber">Last num of my phone-number</option>
                        <option value="name">my name</option>
                        <option value="birthday">my birthday</option>
                    </select></td>
                </tr>
                <tr>
                    <td>密保答案</td>
                    <td><input type="text" id="answer" name="answer"></td>
                </tr>
                <tr>
                    <td>验证码</td>
                    <td><input type="text" id="checkCode" name="checkCode"></td>
                </tr>
                <tr>
                    <td><input type="text" id="checkCodeValue" class="unchanged"
                               style="width: 60px; border: 0px;background-color: coral" readonly="readonly"></td>
                    <td><a id="refreshCode" class="unchanged" style="border: 0px" onclick="createCode()">看不清,换一张</a>
                    </td>
                </tr>
            </table>
        </form>
        <p>
            <input type="button" id="loginSubmit" name="submit" class="submitData" value="提交" onclick="register()" \>
            <input type="button" id="registerSubmit" name="submit" class="submitData" value="重置" onclick="cleanAll()" \>
        </p>
    </div>
    <div class="div_right"></div>
</div>
</body>
</html>
