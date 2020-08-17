//查询购物车里所有商品
function allGoods() {
    $.ajax({
        type: "post",
        url: "/selectAllFromCart",
        cache: false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            $("#tabResult").empty();//每次载入前先清空数据，以防重复加载
            $("#tabButton").empty();
            var result = data.result;
            var books = data.cartEntity;
            // if (result == "success"){}else {$("#tabResult").append("<p style='color: red'>您的购物车空空的，赶快去添加商品吧</p>");}
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
            var count = 0;
            for (var i = 0; i < books.length; i++) {
                var book = books[i];
                var p = parseFloat(book.price);
                var n = parseInt(book.number);
                var sum = p * n;
                count = sum + count;
                $("#books tbody").append("<tr>"
                    + "<td name='cart_id' id='cart_id' style='display: none' class='cart_id'>" + book.cartId + "</td>"
                    + "<td name='book_name'>" + book.bookName + "</td>"
                    + "<td name='book_price'>" + book.price + "</td>"
                    + "<td name='book_stock'>" + book.stock + "</td>"
                    + "<td name='book_author'>" + book.author + "</td>"
                    + "<td name='book_number'>" + book.number + "</td>"
                    + "<td>" + "<button type='button' id='deleteFromCart' name='deleteFromCart' onclick='deleteFromCart(this)'>删除</button>" + "</td>"
                    + "</tr>"
                );
            }
            $("#tabButton").append("<br/>"
                + "<button type='button' id='countAll' name='countAll' onclick='countPrice(this)'>结算</button>&nbsp;&nbsp;&nbsp;&nbsp;"
                + "您当前总共需要支付<a href='#' name='countPrice' style='color: red'>" + count + "</a>元&nbsp;&nbsp;&nbsp;&nbsp;"
                + "<button type='button' id='cleanAll' name='cleanAll' onclick='cleanAll()'>清空购物车</button>"
            );
        },
        error: function () {
            alert("服务器出错")
        }
    });
    $("#book_id").hide();//--隐藏id栏
}
//结算
function countPrice(item) {
    var n = $(item).parent();
    var count = $(n).children("a[name='countPrice']").text();
    $("#tabAddress").empty();
    $("#tabAddress").append("<br/><table>"
        + "<tr style='display: none' id='cart_count'><td>总价</td><td id='cart_id_count' name='cart_id_count' class='cart_id_count'>" + count + "</td></tr>"
        + "<tr><td>收货人姓名</td><td name='book_address_name'>" + "<input type='text' id='address_name' name='address_name'>" + "</td></tr>"
        + "<tr><td>收货人电话</td><td name='book_address_tel'>" + "<input type='text' id='address_tel' name='address_tel'>" + "</td></tr>"
        + "<tr><td>收货人地址</td><td name='book_address_address'>" + "<input type='text' id='address_address' name='address_address'>" + "</td></tr>"
        + "</table>"
        + "<br/>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
        + "<button type='button' id='buttonAddressInfo' name='buttonAddressInfo' onclick='submitGoods()'>确定</button>"
    );
    $("#cart_count").hide();//--隐藏id栏
}
//结算：确定
function submitGoods() {
    var cPrice = $("#cart_id_count").text();
    var address_name = $("#address_name").val();
    var address_tel = $("#address_tel").val();
    var address_address = $("#address_address").val();
    var addressInfo = {
        addressName:address_name,
        addressTel:address_tel,
        address:address_address,
        count:cPrice
    }
    var data = JSON.stringify(addressInfo);
    $.ajax({
        type:"post",
        data:data,
        url:"/countCartGoods",
        cache:false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            var result = data;
            if (result == "success") {
                alert("下单成功，您的购物车空空的，赶快去添加商品吧");
                allGoods();
                $("#tabAddress").empty();
            } else {
                alert("下单失败，请重新提交");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}
//清空购物车
function cleanAll() {
    console.log("cleanAll");
    $.ajax({
        type:"post",
        url:"/deleteAllFromCart",
        cache:false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            var result = data;
            if (result == "success") {
                alert("清空成功，您的购物车空空的，赶快去添加商品吧");
                allGoods();
            } else {
                alert("清空失败，请重新删除");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}
//删除
function deleteFromCart(item) {
    //找到tr元素
    var n = $(item).parent().parent();
    var id = $(n).children("td[name='cart_id']").text();
    var dataId = JSON.stringify(id);
    $.ajax({
        type:"post",
        data:dataId,
        url:"/deleteFromCart",
        cache:false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            var result = data;
            if (result == "success") {
                alert("删除成功");
                allGoods();
            } else {
                alert("删除失败，请重新删除");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}