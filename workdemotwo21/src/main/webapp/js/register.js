//定义全局变量
var code;
// 验证码生成
function createCode() {
    code = '';
    var refreshCode = document.getElementById("refreshCode");
    var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l',
        'm','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K',
        'L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
    for(var i = 0; i < 4; i++){
        var charIndex = Math.floor(Math.random()*59);
        code += selectChar[charIndex];
    }
    var checkCodeValue = document.getElementById("checkCodeValue");
    if (refreshCode){
        checkCodeValue.className = "code";
        checkCodeValue.value = code;
    }
}
//注册检测
function validate() {
    //用户名格式验证
    var username = $('#userName').val();
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
    //确认密码
    var confirmPassword = $("#confirmPassword").val();
    if (password != confirmPassword){
        alert("两次输入密码不相同，请重新输入")
        return false;
    }
    //姓名
    //电话
    //住址，城市，国家
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
    //验证码检测
    var checkCode = $("#checkCode").val();
    if ("" != checkCode){
        if(code.toUpperCase() != checkCode.toUpperCase()){
            alert("验证码输入错误，请重新输入");
            return false;
        }
        return true;
    }else {
        alert("请输入验证码");
        return false;
    }
    return true;
}
//注册
function register() {
    var checkResult = validate();
    if (checkResult){
        console.log("验证通过");
        $.ajax("/user/register",{
            type:"POST",
            data:$('#form').serialize(),
            success: function(result){
                if("success"==result){
                    alert("注册成功，去登陆吧")
                    window.location.href = "/login.jsp";
                }else {
                    alert("该用户已存在（用户名），请重新输入");
                }
            },
            error:function(err) {
                alert("服务器出错");
            }
        });
    }
}
