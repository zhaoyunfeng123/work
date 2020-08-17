function myShoppingCart() {
    var name = userNameSession;
    $.ajax({
        type: "post",
        url: "/selectAllCartBook",
        data:{userName:name},
        cache: false,
        dataType: 'json',
        success: function (data) {
            //每次载入前先清空数据，以防重复加载
            $("#div_right").empty();
            $("#div_right").append(
                "<div>我的购物车</div>"
                +"<table id='userInfo' class='tab-list' border='1'>"
                + "<thead> "
                + "<td>商品名称</td>"
                + "<td>商品单价</td>"
                + "<td>购买数量</td>"
                + "<td>金额</td>"
                + "<td>编辑</td>"
                + "</tr> </thead>"
                + "<tbody id='tbody_userInfo'>"
                + "</tbody>"
                + "<td colspan='5'><a href='#' id=\"bookInfo\" onclick=\"bookInfo()\">继续购物</a>&nbsp;&nbsp;<a href='#' id=\"cleanCart\" onclick=\"cleanCart()\">清空购物车</a>&nbsp;&nbsp;<a href='#' id=\"submitOrder\" onclick=\"submitOrder()\">提交订单</a></td>"
                +"</table>"
            );
            var carts = data;//传的是list
            for (var i = 0; i < carts.length; i++) {
                var book = carts[i];
                var p = parseFloat(book.price);
                var n = parseInt(book.number);
                var count = n * p;
                $("#tbody_userInfo").append("<tr>"
                    + "<td id='book_id' name='book_id'class='book_id' style='display: none'>" + book.bookId + "</td>"
                    + "<td id='book_id' name='cart_id'class='cart_id' style='display: none'>" + book.cartId + "</td>"
                    + "<td name='book_name'>" + book.bookName + "</td>"
                    + "<td name='book_price'>" + book.price + "</td>"
                    + "<td name='book_number' id='updateNumber' name='updateNumber' onclick='updateNumber(this)' class='updateNumber'> "+ book.number + "</td>"
                    + "<td name='book_count'>" + count + "</td>"
                    + "<td ><a href='#' id='cleanByCartId' onclick='cleanByCartId(this)'>退回商品架</a></td>"
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
//退回商品架
function cleanByCartId(item) {
    var n = $(item).parent().parent();
    var cart_id = $(n).children("td[name='cart_id']").text();
    console.log(cart_id);
    var name = userNameSession;
    var parparmData = {
        cartId:cart_id,
        userName:name
    };
    $.ajax({
        type: "post",
        url: "/cleanBookByCartId",
        data:parparmData,
        cache: false,
        success: function (data){
            if ("success" == data){
                alert("退回成功，去购物吧");
                myShoppingCart();
            }else {
                alert("退回失败，请重试");
            }
        },
        error: function () {
            alert("服务器出错")
        }
    });
}
//清空购物车
function cleanCart() {
    var name = userNameSession;
    $.ajax({
        type: "post",
        url: "/cleanCartBook",
        data:{userName:name},
        cache: false,
        // dataType: 'json',
        success: function (data){
            if ("success" == data){
                alert("清空成功，去购物吧");
                myShoppingCart();
            }else {
                alert("清空失败，请重试");
            }
        },
        error: function () {
            alert("服务器出错")
        }
    });
}
//提交订单
function submitOrder() {
    var name = userNameSession;
    $.ajax({
        type: "post",
        url: "/selectAllCartBook",
        data:{userName:name},
        cache: false,
        dataType: 'json',
        success: function (data) {
            //每次载入前先清空数据，以防重复加载
            $("#div_right").empty();
            $("#div_right").append(
                "<div>我的购物车</div>"
                +"<table id='userInfo' class='tab-list' border='1'>"
                + "<thead> "
                + "<td>商品名称</td>"
                + "<td>商品单价</td>"
                + "<td>购买数量</td>"
                + "<td>金额</td>"
                + "</tr> </thead>"
                + "<tbody id='tbody_userInfo'>"
                + "</tbody>"
                +"</table>"
            );
            var carts = data;//传的是list
            var countAllPrice = 0;
            for (var i = 0; i < carts.length; i++) {
                var book = carts[i];
                var p = parseFloat(book.price);
                var n = parseInt(book.number);
                var count = n * p;
                $("#tbody_userInfo").append("<tr>"
                    + "<td id='book_id' name='book_id'class='book_id' style='display: none'>" + book.bookId + "</td>"
                    + "<td id='book_id' name='cart_id'class='cart_id' style='display: none'>" + book.cartId + "</td>"
                    + "<td name='book_name'>" + book.bookName + "</td>"
                    + "<td name='book_price'>" + book.price + "</td>"
                    + "<td name='book_number'>" + book.number + "</td>"
                    + "<td name='book_count'>" + count + "</td>"
                    + "</tr>"
                );
                countAllPrice = countAllPrice + count;
            }
            $("#tbody_userInfo").append(
                "<tr><td colspan='4' id='countAllPrice'>总计：" + countAllPrice + "</td></tr>"
                +"<tr><td colspan='4'>收获地址：<input type='text' name='text_address' id='text_address'></td></tr>"
                +"<tr><td colspan='4'><a href='#' id='bookInfo' onclick='bookInfo()'>继续购物</a>&nbsp;&nbsp;<a href='#' id='go_myShoppingCart' onclick='myShoppingCart()'>返回购物车</a>&nbsp;&nbsp;<input type='button' id='button_purchase' name='button_purchase' value='确认购买' onclick='buttonPurchase(this)'></td></tr>"
            );
        },
        error: function () {
            alert("服务器出错")
        }
    });
    $("#book_id").hide();//--隐藏
}
//确认购买
function buttonPurchase(item) {
    var address = $("#text_address").val();
    var countAllPrice = $("#countAllPrice").text();
    var c = countAllPrice.split("：")[1];
    var name = userNameSession;
    var parparmData = {
        userName:name,
        address:address,
        count:c
    };
    $.ajax({
        type: "post",
        url: "/placeOrder",
        data:parparmData,
        cache: false,
        // dataType: 'json',
        success: function (data){
            if ("success" == data){
                alert("下单成功，去查看吧");
                cleanCart();
            }else {
                alert("下单失败，请重试");
            }
        },
        error: function () {
            alert("服务器出错")
        }
    });
}
//修改购买数量
function updateNumber(object) {
    $(object).html("<input type='text' value='"+$(object).text()+"'/>");
    var ed = $(object).children("input").val();
    console.log(ed);
}
function f() {
    
}
