//功能1、添加图书信息
function addBookInfo() {
    //每次载入前先清空数据，以防重复加载
    $("#div_right").empty();
    $("#div_right").append(
        "<div>图书添加</div>"
        +"<table id='books' class='tab-list' border='1'>"
        +"<tr> <td>图书id</td> <td><input type='text' id='book_id' name='book_id'></td> </tr>"
        +"<tr> <td>图书名称</td> <td><input type='text' id='book_name' name='book_name'></td> </tr>"
        +"<tr> <td>图书作者</td> <td><input type='text' id='book_author' name='book_author'></td> </tr>"
        +"<tr> <td>出版社</td> <td><input type='text' id='book_publisher' name='book_publisher'></td> </tr>"
        +"<tr> <td>图书价格</td> <td><input type='text' id='book_price' name='book_price'></td> </tr>"
        +"<tr> <td>图书数量</td> <td><input type='text' id='book_stock' name='book_stock'></td> </tr>"
        +"<tr> <td>图书信息</td> <td><input type='text' id='book_info' name='book_info'></td> </tr>"
        // +"<tr> <td>图书图片</td> <td><input type='text' id='book_img' name='book_img'></td> </tr>"
        +"<tr> <td><input type='button' id='book_button' name='book_button' value='确认添加' onclick='goAddBook()'></td> <td><a href='/adminIndex.jsp'>返回管理页</a></td> </tr>"
        +"</table>"
    );
}
//确认添加
function goAddBook() {
    console.log("goAddBook");
    var id = $("#book_id").val();
    var name = $("#book_name").val();
    var author = $("#book_author").val();
    var publisher=$("#book_publisher").val();
    var price=$("#book_price").val();
    var stock=$("#book_stock").val();
    var info=$("#book_info").val();
    var bookData ={
        bookId:id,
        bookName:name,
        author:author,
        publisher:publisher,
        price:price,
        stock:stock,
        info:info
        // img:$("#book_img").val()
    };
    $.ajax({
        type:"post",
        data:bookData,
        url:"/admin/addBook",
        cache:false,
        success:function (data) {
            if("success" == data){
                alert("添加图书成功");
            }else {
                alert("添加图书失败，请重新添加");
            }
        },
        error:function(err) {
            alert("服务器出错");
        }
    });
}
//功能2、查改图书信息
//显示所有图书
function updateBookInfo() {
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
                "<div>图书信息管理</div>"
                +"<table id='bookInfo' class='tab-list' border='1'>"
                + "<thead> "
                +"<tr><td><input type='text' id='select_book_id' name='select_book_id' value='输入图书id进行查询'></td>" +
                "<td colspan='4'><input type='button' id='select_button' name='select_button' value='查询' onclick='selectBook(this)'></td></tr>"
                +"<tr>"
                + "<td>商品id</td>"
                + "<td>商品缩图</td>"
                + "<td>商品摘要</td>"
                + "<td>信息修改</td>"
                + "<td>删除操作</td>"
                + "</tr> </thead>"
                + "<tbody>"
                + "</tbody>"
                +"</table>"
            );
            var books = data;
            for (var i = 0; i < books.length; i++) {
                var book = books[i];
                $("#bookInfo tbody").append("<tr>"
                    + "<td name='book_id'>" + book.bookId + "</td>"
                    + "<td name='book_img'>" + book.img + "</td>"
                    + "<td name='book_info'>" + "书名："+book.bookName +"<br/>作者："+ book.author +"<br/>出版社："+ book.publisher+"<br/>价格："+ book.price + "元</td>"
                    + "<td ><a href='#' id='updateBook' onclick='updateBook()'>修改图书信息</a></td>"
                    + "<td ><a href='#' id='deleteBook' onclick='deleteBook(this)'>删除图书信息</a></td>"
                    + "</tr>"
                );
            }
        },
        error: function () {
            alert("服务器出错")
        }
    });
}
//根据图书id搜索图书
function selectBook(item) {
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
                    "<div>图书信息管理</div>"
                    +"<table id='bookInfo' class='tab-list' border='1'>"
                    + "<thead> "
                    +"<tr><td><input type='text' id='select_book_id' name='select_book_id' value='输入图书id进行查询'></td>" +
                    "<td colspan='4'><input type='button' id='select_button' name='select_button' value='查询' onclick='selectBook(this)'></td></tr>"
                    +"<tr>"
                    + "<td>商品id</td>"
                    + "<td>商品缩图</td>"
                    + "<td>商品摘要</td>"
                    + "<td>信息修改</td>"
                    + "<td>删除操作</td>"
                    + "</tr> </thead>"
                    + "<tbody>"
                    + "</tbody>"
                    +"</table>"
                );
                var book = data.book;
                    $("#bookInfo tbody").append("<tr>"
                        + "<td name='book_id'>" + book.bookId + "</td>"
                        + "<td name='book_img'>" + book.img + "</td>"
                        + "<td name='book_info'>" + "书名："+book.bookName +"<br/>作者："+ book.author +"<br/>出版社："+ book.publisher+"<br/>价格："+ book.price + "元</td>"
                        + "<td ><a href='#' id='updateBook' onclick='updateBook(this)'>修改图书信息</a></td>"
                        + "<td ><a href='#' id='deleteBook' onclick='deleteBook(this)'>删除图书信息</a></td>"
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
}
//删除图书
function deleteBook(item) {
    //获取tr元素
    var n = $(item).parent().parent();
    //获取图书id
    var id = $(n).children("td[name='book_id']").text();
    console.log(id);
    var paramdata = {
        bookId:id
    };
    $.ajax({
        type: "post",
        data: paramdata,
        url: "/deleteBookById",
        cache: false,
        success: function (data) {
            var result = data;
            if (result == "success") {
                alert("删除成功");
                updateBookInfo();
            } else {
                alert("删除失败，请重新删除");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}
//修改图书
function updateBook(item) {
    //获取tr元素
    var n = $(item).parent().parent();
}