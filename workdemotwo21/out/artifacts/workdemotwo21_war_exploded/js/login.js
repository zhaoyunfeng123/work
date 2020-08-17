// 登录检测
function validate() {
    //用户名检测
    var userName = $("#userName").val();
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
    return true;
}

//登录
function login() {
    var type = $("input[name='userType']:checked").val();
    var userName = $("#userName").val();
    var password = $("#password").val();
    var paramdata = {
        userName:userName,
        password:password,
        userType:type
    };
    console.log(userName+password+type);
    if (validate()){
        $.ajax({
            type:"post",
            data:paramdata,
            url:"/user/login",
            cache:false,
            // dataType: 'json',
            success:function (data) {
                if("success" == data){
                    if (type == "用户"){
                        //跳转到用户主页面
                        alert("登陆成功");
                        window.location.href = "/index.jsp";
                    } else {
                        //跳转到管理员主页面
                        alert("登陆成功");
                        window.location.href = "/adminIndex.jsp";
                    }
                }else {
                    alert("登陆失败，请重新登录");
                }
            },
            error:function(err) {
                alert("服务器出错");
            }
        });
    }
}
//去注册
function goRegister() {
    window.location.href = "/register.jsp";
}