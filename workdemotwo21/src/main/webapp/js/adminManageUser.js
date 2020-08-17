//查改用户信息
//显示所有用户
function allUserInfo() {
    $.ajax({
        type: "post",
        url: "/admin/selectAllUser",
        cache: false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        dataType: 'json',
        success: function (data) {
            //每次载入前先清空数据，以防重复加载
            $("#div_right").empty();
            $("#div_right").append(
                "<div>用户管理</div>"
                + "<table id='bookInfo' class='tab-list' border='1'>"
                + "<thead> "
                + "<tr><td><input type='text' id='select_user_id' name='select_user_id' value='输入用户id进行查询'></td>" +
                "<td colspan='9'><input type='button' id='select_button' name='select_button' value='查询' onclick='selectUser(this)'></td></tr>"
                + "<tr>"
                + "<td>用户id</td>"
                + "<td>用户密码</td>"
                + "<td>用户姓名</td>"
                + "<td>用户邮箱</td>"
                + "<td>用户爱好</td>"
                + "<td>密保问题</td>"
                + "<td>密保答案</td>"
                + "<td>联系电话</td>"
                + "<td>修改操作</td>"
                + "<td>删除操作</td>"
                + "</tr> </thead>"
                + "<tbody id='tbody_bookInfo'>"
                + "</tbody>"
                + "<td colspan='10'><a href='/register.jsp'>添加用户信息</a></td>"
                + "</table>"
            );
            var users = data;
            for (var i = 0; i < users.length; i++) {
                var user = users[i];
                $("#tbody_bookInfo").append("<tr>"
                    + "<td name='user_userName'>" + user.userName + "</td>"
                    + "<td name='user_password'>" + user.password + "</td>"
                    + "<td name='user_fullName'>" + user.fullName + "</td>"
                    + "<td name='user_email'>" + user.email + "</td>"
                    + "<td name='user_hobby'>" + user.hobby + "</td>"
                    + "<td name='user_problem'>" + user.problem + "</td>"
                    + "<td name='user_answer'>" + user.answer + "</td>"
                    + "<td name='user_tel'>" + user.tel + "</td>"
                    + "<td name='user_update'><a href='#' id='updateBook' onclick='updateUser(this)'>修改信息</a></td>"
                    + "<td ><a href='#' id='deleteBook' onclick='deleteUser(this)'>删除信息</a></td>"
                    + "</tr>"
                );
            }
        },
        error: function () {
            alert("服务器出错")
        }
    });
}
//修改用户信息
function updateUser(item) {
    //tr
    var n = $(item).parent().parent();
    //td
    var password = $(n).children("td[name='user_password']");
    var fullName = $(n).children("td[name='user_fullName']");
    var email = $(n).children("td[name='user_email']");
    var hobby = $(n).children("td[name='user_hobby']");
    var problem = $(n).children("td[name='user_problem']");
    var answer = $(n).children("td[name='user_answer']");
    var tel = $(n).children("td[name='user_tel']");
    var operation = $(n).children("td[name='user_update']");
    //保存原始数据
    $(password).data("password",$(password).text());
    $(fullName).data("fullName",$(fullName).text());
    $(email).data("email",$(email).text());
    $(hobby).data("hobby",$(hobby).text());
    $(problem).data("problem",$(problem).text());
    $(answer).data("answer",$(answer).text());
    $(tel).data("tel",$(tel).text());
    //输入
    password.html("<input type='text' name='user_input_password' value='"+$(n).children("td[name='user_password']").text()+"'/>");
    fullName.html("<input type='text' name='user_input_fullName' value='"+$(n).children("td[name='user_fullName']").text()+"'/>");
    email.html("<input type='text' name='user_input_email' value='"+$(n).children("td[name='user_email']").text()+"'/>");
    hobby.html("<input type='text' name='user_input_hobby' value='"+$(n).children("td[name='user_hobby']").text()+"'/>");
    problem.html("<input type='text' name='user_input_problem' value='"+$(n).children("td[name='user_problem']").text()+"'/>");
    answer.html("<input type='text' name='user_input_answer' value='"+$(n).children("td[name='user_answer']").text()+"'/>");
    tel.html("<input type='text' name='user_input_tel' value='"+$(n).children("td[name='user_tel']").text()+"'/>");
    //确认和取消按钮
    operation.html("<input type='button' value='确认' onclick='submitUpdateUserInfo(this)'/>&nbsp;&nbsp;<input type='button' value='取消' onclick='cancelUpdateUserInfo(this)'/>");
}
//修改---确定
function submitUpdateUserInfo(item) {
    //tr
    var n = $(item).parent().parent();
    //input
    var name = $(n).children("td[name='user_userName']").text();
    var password = $(n).children().children("input[name='user_input_password']").val();
    var fullName = $(n).children().children("input[name='user_input_fullName']").val();
    var email = $(n).children().children("input[name='user_input_email']").val();
    var hobby = $(n).children().children("input[name='user_input_hobby']").val();
    var problem = $(n).children().children("input[name='user_input_problem']").val();
    var answer = $(n).children().children("input[name='user_input_answer']").val();
    var tel = $(n).children().children("input[name='user_input_tel']").val();
    var parparmData = {
        userName:name,
        password:password,
        fullName:fullName,
        email:email,
        hobby:hobby,
        problem:problem,
        answer:answer,
        tel:tel
    };
    $.ajax({
        type: "post",
        data: parparmData,
        url: "/admin/updateUser",
        cache: false,
        // dataType:"JSON",
        success: function (data) {
            var result = data;
            if (result == "success") {
                alert("修改成功");
                allUserInfo();
            } else {
                alert("修改失败，请重新修改");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });

}
//修改---取消
function cancelUpdateUserInfo(item) {
    //tr
    var n = $(item).parent().parent();
    //td
    var password = $(n).children("td[name='user_password']");
    var fullName = $(n).children("td[name='user_fullName']");
    var email = $(n).children("td[name='user_email']");
    var hobby = $(n).children("td[name='user_hobby']");
    var problem = $(n).children("td[name='user_problem']");
    var answer = $(n).children("td[name='user_answer']");
    var tel = $(n).children("td[name='user_tel']");
    var operation = $(n).children("td[name='user_update']");
    //获取原始数据
    var pwd = $(password).data("password");
    var fName = $(fullName).data("fullName");
    var ema = $(email).data("email");
    var ho = $(hobby).data("hobby");
    var pro = $(problem).data("problem");
    var ans = $(answer).data("answer");
    var t = $(tel).data("tel");
    //返回原样
    password.text(pwd);
    fullName.text(fName);
    email.text(ema);
    hobby.text(ho);
    problem.text(pro);
    answer.text(ans);
    tel.text(t);
    operation.html("<a href='#' id='updateBook' onclick='updateUser(this)'>修改信息</a>");
}
//删除信息
function deleteUser(item) {
    var n = $(item).parent().parent();
    var name = $(n).children("td[name='user_userName']").text();
    console.log(name);
    var parparmData = {userName: name};
    $.ajax({
        type: "post",
        data: parparmData,
        url: "/admin/deleteUser",
        cache: false,
        // dataType:"JSON",
        success: function (data) {
            var result = data;
            if (result == "success") {
                alert("删除成功");
                allUserInfo();
            } else {
                alert("删除失败，请重新删除");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}

//搜索
function selectUser(item) {
    //获取tr元素
    var n = $(item).parent().parent();
    //获取图书id
    var id = $(n).children().children("input[name='select_user_id']").val();
    console.log(id);
    var paramdata = {
        userName: id
    };
    $.ajax({
        type: "post",
        url: "/admin/selectUserByUserName",
        data: paramdata,
        cache: false,
        dataType: 'json',
        success: function (data) {
            //每次载入前先清空数据，以防重复加载
            $("#div_right").empty();
            $("#div_right").append(
                "<div>用户管理</div>"
                + "<table id='bookInfo' class='tab-list' border='1'>"
                + "<thead> "
                + "<tr><td><input type='text' id='select_user_id' name='select_user_id' value='输入用户id进行查询'></td>" +
                "<td colspan='9'><input type='button' id='select_button' name='select_button' value='查询' onclick='selectUser(this)'></td></tr>"
                + "<tr>"
                + "<td>用户id</td>"
                + "<td>用户密码</td>"
                + "<td>用户姓名</td>"
                + "<td>用户邮箱</td>"
                + "<td>用户爱好</td>"
                + "<td>密保问题</td>"
                + "<td>密保答案</td>"
                + "<td>联系电话</td>"
                + "<td>修改操作</td>"
                + "<td>删除操作</td>"
                + "</tr> </thead>"
                + "<tbody id='tbody_bookInfo2'>"
                + "</tbody>"
                + "<td colspan='10'><a href='/register.jsp'>添加用户信息</a></td>"
                + "</table>"
            );
            var user = data.user;
            $("#tbody_bookInfo2").append("<tr>"
                + "<td name='user_userName'>" + user.userName + "</td>"
                + "<td name='user_password'>" + user.password + "</td>"
                + "<td name='user_fullName'>" + user.fullName + "</td>"
                + "<td name='user_email'>" + user.email + "</td>"
                + "<td name='user_hobby'>" + user.hobby + "</td>"
                + "<td name='user_problem'>" + user.problem + "</td>"
                + "<td name='user_answer'>" + user.answer + "</td>"
                + "<td name='user_tel'>" + user.tel + "</td>"
                + "<td ><a href='#' id='updateBook' onclick='updateUser(this)'>修改信息</a></td>"
                + "<td ><a href='#' id='deleteBook' onclick='deleteUser(this)'>删除信息</a></td>"
                + "</tr>"
            );
        },
        error: function () {
            alert("服务器出错")
        }
    });
}