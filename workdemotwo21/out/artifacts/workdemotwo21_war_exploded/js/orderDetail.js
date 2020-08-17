//我的订单
function myOrder() {
    var name = userNameSession;
    $.ajax({
        type: "post",
        url: "/selectMyOrder",
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
            var orderList = data;//传的是list
            for(var j =0; j < orderList.length; j++){
                var detailList = orderList[j].orderDetailBeanList;
                for (var i = 0; i < detailList.length; i++) {
                    var book = detailList[i];
                    $("#tbody_userInfo").append("<tr>"
                        + "<td name='book_name'>" + book.bookName + "</td>"
                        + "<td name='book_price'>" + book.price + "</td>"
                        + "<td name='book_number'>" + book.number + "</td>"
                        + "<td name='book_count'>" + book.countBook + "</td>"
                        + "</tr>"
                    );
                }
                var order = orderList[j];
                $("#tbody_userInfo").append(
                    "<tr><td colspan='2' id='countAllPrice'>总计：" + order.count + "</td>" +
                    "<td colspan='2' id='createTime'>下单时间：" + order.createCartTime + "</td></tr>"
                    +"<tr><td colspan='4' id='address'>收货地址：" + order.address + "</td></tr>"
                );
            }

        },
        error: function () {
            alert("服务器出错")
        }
    });
}