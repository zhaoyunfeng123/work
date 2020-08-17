//用户退出
function loginOut() {
    $.ajax({
        type: "post",
        url: "/loginOut",
        cache: false,
        // dataType: 'json',
        success: function (data){
            if ("success" == data){
                alert("退出成功");
                window.location.href = "/login.jsp";
            }else {
                alert("退出失败，请重试")
            }
        },
        error: function () {
            alert("服务器出错")
        }
    });
}