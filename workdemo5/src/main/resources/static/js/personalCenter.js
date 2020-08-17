//查询所有订单
function allOrder() {
    $.ajax({
        type: "post",
        url: "/selectAllOrder",
        cache: false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            //每次载入前先清空数据，以防重复加载
            $("#tab-title").empty();
            $("#tabResult").empty();
            var orderList = data.orderList;
            // if (result == "success"){}else {$("#tabResult").append("<p style='color: red'>您没有订单，赶快去购买商品吧</p>");}
            for (var i = 0; i < orderList.length; i++) {
                var order = orderList[i];
                $("#tab-title").append(
                    "<table border='1'>"
                    + "<tr><td>订单编号</td>"
                    + "<td>" + order.orderId + "</td>"
                    + "</tr><tr><td>收货人</td>"
                    + "<td>" + order.addressName + "</td>"
                    + "</tr><tr><td>电话</td>"
                    + "<td>" + order.addressTel + "</td>"
                    + "</tr><tr><td>收货地址</td>"
                    + "<td>" + order.address + "</td>"
                    + "</tr><tr><td>总价</td>"
                    + "<td>" + order.count + "元</td>"
                    + "</tr><tr><td>购买时间</td>"
                    + "<td>" + order.createCartTime + "</td>"
                    + "</tr></table>"
                    + "<table id='books' class='tab-list' border='1'>"
                    + "<thead> <tr>"
                    + "<th>书名</th>"
                    + "<th>单价</th>"
                    + "<th>作者</th>"
                    + "<th>购买数量</th>"
                    + "</tr> </thead>"
                    + "<tbody>"
                    + "</tbody> </table>");
                var orderDetailList = order.orderDetailEntityList;
                for (var j = 0; j < orderDetailList.length; j++) {
                    var orderDetail = orderDetailList[j];
                    // $("#books tbody").append(
                    $("#tab-title").append(
                        "<table border='1' class='tab-list'><tr>"
                        + "<th>" + orderDetail.bookName + "</th>"
                        + "<th>" + orderDetail.price + "</th>"
                        + "<th>" + orderDetail.author + "</th>"
                        + "<th>" + orderDetail.number + "</th>"
                        + "</tr></table>"
                    );
                }
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}