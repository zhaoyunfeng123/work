function selectButton() {
    var praamdata = {
        bookName: $("#bookName").val(),
        author: $("#author").val()
    }
    var selectpraamdata = JSON.stringify(praamdata);
    $.ajax({
        type: "post",
        data: selectpraamdata,
        url: "/selectIndex",
        caches: false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            $("#tabResult").empty();//每次载入前先清空数据，以防重复加载
            // if (data.e){console.log("---------------------------")}  //怎么判断为空？
            $("#tabResult").append(
                "<table id='books' class='tab-list' border='1'>"
                + "<thead> <tr>"
                + "<th>书名</th>"
                + "<th>价格</th>"
                + "<th>库存</th>"
                + "<th>作者</th>"
                + "<th>购买数量</th>"
                + "<th>操作</th>"
                + "</tr> </thead>"
                + "<tbody>"
                + "</tbody> </table>");
            for (var i = 0; i < data.length; i++) {
                var book = data[i];
                $("#books tbody").append(
                    "<tr>" + "<td name='book_id' style='display: none' class='book_id'>" + book.bookId + "</td>"
                    + "<td name='book_name'>" + book.bookName + "</td>"
                    + "<td name='book_price'>" + book.price + "</td>"
                    + "<td name='book_stock'>" + book.stock + "</td>"
                    + "<td name='book_author'>" + book.author + "</td>"
                    + "<td>" + "<input type='text' id='number' name='number'>" + "</td>"
                    + "<td>" + "<button type='button' id='addCart' name='addCart' onclick='addCart(this)'>加入购物车</button>" + "</td>"
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
//加入购物车
function addCart(item) {
    //获取tr元素
    var n = $(item).parent().parent();
    //获取书的信息
    var id = $(n).children("td[name='book_id']").text();
    var name = $(n).children("td[name='book_name']").text();
    var price = $(n).children("td[name='book_price']").text();
    var stock = $(n).children("td[name='book_stock']").text();
    var author = $(n).children("td[name='book_author']").text();
    //获取购买数量
    var number = $(n).children().children("input[name='number']").val();
    if (number != "") {
        var paramdata = {
            bookId: id,
            bookName: name,
            price: price,
            stock: stock,
            author: author,
            number: number
        }
        var cartData = JSON.stringify(paramdata);
        $.ajax({
            type: "post",
            data: cartData,
            url: "/addToCart",
            cache: false,
            headers: {"Content-Type": "application/json;charset=utf-8"},
            success: function (data) {
                var result = data;
                if (result == "success") {
                    alert("加入购物车成功");
                } else {
                    alert("加入购物车失败，请重新添加");
                }
            },
            error: function (err) {
                alert("服务器出错");
            }
        });
    } else {
        alert("请输入购买数量");
    }
}

