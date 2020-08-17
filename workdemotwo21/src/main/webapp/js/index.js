//功能1、首页
function bookInfo() {
    $.ajax({
        type: "post",
        url: "/admin/selectAllBook",
        cache: false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        dataType: 'json',
        success: function (data) {
            //每次载入前先清空数据，以防重复加载
            $("#div_right").empty();
            $("#div_right").append(
                "<div>在线购物</div>"
                +"<table id='userInfo' class='tab-list' border='1'>"
                + "<thead> "
                +"<tr><td><input type='text' id='select_book_id' name='select_book_id' value='输入图书id进行查询'></td>" +
                "<td colspan='4'><input type='button' id='select_button' name='select_button' value='查询' onclick='userSelectBook(this)'></td></tr>"
                +"<tr>"
                + "<td>商品缩图</td>"
                + "<td>商品摘要</td>"
                + "<td>在线购买</td>"
                + "</tr> </thead>"
                + "<tbody id='tbody_userInfo'>"
                + "</tbody>"
                + "<td colspan='3'><a href='#'>查看购物车</a>&nbsp;&nbsp;<a href='#'>清空购物车</a></td>"
                +"</table>"
            );
            var books = data;
            for (var i = 0; i < books.length; i++) {
                var book = books[i];
                $("#tbody_userInfo").append("<tr>"
                    + "<td id='book_id' name='book_id'class='book_id' style='display: none'>" + book.bookId + "</td>"
                    + "<td name='book_img'>" + book.img + "</td>"
                    // + "<td name='book_img'><img STYLE='width: 100px;height: 100px' src='/book.img'/></td>"
                    + "<td name='book_info'>" + "书名："+book.bookName +"<br/>作者："+ book.author +"<br/>出版社："+ book.publisher+"<br/>价格："+ book.price + "元</td>"
                    + "<td ><a href='#' id='addBookToCart' onclick='addBookToCart(this)'>放入购物车</a></td>"
                    + "</tr>"
                );
            }
        },
        error: function () {
            alert("服务器出错")
        }
    });
    $("#book_id").hide();//--隐藏
}
//根据图书id搜索图书
function userSelectBook(item) {
    //获取tr元素
    var n = $(item).parent().parent();
    //获取图书id
    var id = $(n).children().children("input[name='select_book_id']").val();
    console.log(id);
    var paramdata = {
        bookId:id
    };
    $.ajax({
        type: "post",
        data: paramdata,
        url: "/selectBookById",
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data != "fail") {
                //每次载入前先清空数据，以防重复加载
                $("#div_right").empty();
                $("#div_right").append(
                    "<div>在线购物</div>"
                    +"<table id='userInfo' class='tab-list' border='1'>"
                    + "<thead> "
                    +"<tr><td><input type='text' id='select_book_id' name='select_book_id' value='输入图书id进行查询'></td>" +
                    "<td colspan='4'><input type='button' id='select_button' name='select_button' value='查询' onclick='userSelectBook(this)'></td></tr>"
                    +"<tr>"
                    + "<td>商品缩图</td>"
                    + "<td>商品摘要</td>"
                    + "<td>在线购买</td>"
                    + "</tr> </thead>"
                    + "<tbody id='tbody_userInfo'>"
                    + "</tbody>"
                    + "<td colspan='3'><a href='#'>查看购物车</a>&nbsp;&nbsp;<a href='#'>清空购物车</a></td>"
                    +"</table>"
                );
                var book = data.book;
                $("#tbody_userInfo").append("<tr>"
                    + "<td id='book_id' name='book_id'class='book_id' style='display: none'>" + book.bookId + "</td>"
                    + "<td name='book_img'>" + book.img + "</td>"
                    + "<td name='book_info'>" + "书名："+book.bookName +"<br/>作者："+ book.author +"<br/>出版社："+ book.publisher+"<br/>价格："+ book.price + "元</td>"
                    + "<td ><a href='#' id='addBookToCart' onclick='addBookToCart(this)'>放入购物车</a></td>"
                    + "</tr>"
                );
            } else {
                alert("查询失败，请重新查询");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
    $("#book_id").hide();//--隐藏
    $("#book_price").hide();//--隐藏
}
//放入购物车
function addBookToCart(item) {
    var n = $(item).parent().parent();
    var book_id = $(n).children("td[name='book_id']").text();
    var userName = userNameSession;
    console.log("book_id:"+book_id);
    console.log("username:"+userNameSession);
    var paramdata = {
        bookId:book_id,
        userName:userName
    };
    $.ajax({
        type: "post",
        data: paramdata,
        url: "/addBookByIdToCart",
        cache: false,
        success: function (data){
            if ("success" == data){
                alert("添加购物车成功");
            }else {
                alert("添加购物车失败，请重试");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}