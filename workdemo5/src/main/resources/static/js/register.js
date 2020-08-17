function validate() {
    //用户名格式验证
    var username = $('#username').val();
    var regName = /^[A-Za-z0-9]+$/;
    if(username != ''){
        if(!regName.test(username)){
            alert("用户名只能输入字母和数字");
            return false;
        }
    }else {
        alert("用户名不能为空");
        return false;
    }
    //密码格式验证
    var password = $('#password').val();
    var regPwd = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
    if(password != ''){
        if(!regPwd.test(password)){
            alert("密码必须大于8位，且包含大小写字母和数字");
            return false;
        }
    }else {
        alert("密码不能为空");
        return false;
    }
    //邮箱格式验证
    var email = $('#email').val();
    var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(email != ''){
        if(!regEmail.test(email)){
            alert("邮箱输入错误，请重新输入");
            return false;
        }
    }else {
        alert("邮箱不能为空");
        return false;
    }
    return true;
}
function register() {
    var paramdata = {
        userName:$("#username").val(),
        password:$("#password").val(),
        sex:$("input[name='sex']:checked").val(),
        email:$("#email").val()
    };
    var registerdata = JSON.stringify(paramdata);
    if (validate()){
        $.ajax({
            type:"post",
            data:registerdata,
            url:"/register",
            cache:false,
            headers:{"Content-Type" : "application/json;charset=utf-8"},
            success:function (result) {
                if("success" == result){
                    alert("注册成功，去登录吧")
                    window.location.href = "/login.html";
                }
                if ("errorFail" == result){
                    alert("注册失败，请重新注册")
                }
                if ("errorExist" == result){
                    alert("该用户已存在，请重新输入或登录")
                }
                if ("errorNull" == result){
                    alert("注册失败，请重新注册")
                }
            },
            error:function(err) {
                alert("服务器出错");
            }
        });
    }
}