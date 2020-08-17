//定义全局变量
var code;
// 验证码生成
function createCode() {
    code = '';
    var refreshCode = document.getElementById("refreshCode");
    var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l',
        'm','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K',
        'L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z')
    for(var i = 0; i < 6; i++){
        var charIndex = Math.floor(Math.random()*59);
        code += selectChar[charIndex];
    }
    if (refreshCode){
        refreshCode.className = "code";
        refreshCode.value = code;
    }
}
// 登录检测
function validate() {
    //用户名检测
    var userName = $("#username").val();
    if ("" == userName){
        alert("用户名不能为空，请输入用户名");
        return false;
    }
    //密码检测
    var password = $("#password").val();
    if ("" == password){
        alert("密码不能为空，请输入密码");
        return false;
    }
    //验证码检测
    var checkCode = $("#checkCode").val();
    if ("" != checkCode){
        if(code.toUpperCase() != checkCode.toUpperCase()){
        // if(code != checkCode){
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
//登录
function login() {
    var paramdata = {
        userName:$("#username").val(),
        password:$("#password").val(),
        userType:$("input[name='userType']:checked").val()
    };
    var logindata = JSON.stringify(paramdata);
    if (validate()){
        $.ajax({
            type:"post",
            data:logindata,
            url:"/login",
            cache:false,
            headers:{"Content-Type" : "application/json;charset=utf-8"},
            success:function (data) {
                var type = data.userType;
                var result = data.result;
                if("success" == result){
                    if (type == 1){
                        //跳转到用户主页面
                        window.location.href = "/index.html";
                    }else {
                        //跳转到管理员主页面
                        window.location.href = "/adminIndex.html";
                    }
                }else {
                    alert("输入信息有误，请重新输入")
                }
            },
            error:function(err) {
                alert("服务器出错");
            }
        });
    }
}