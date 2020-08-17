//新增图书
function addButton() {
    var name = $("#bookName").val();
    var price = $("#price").val();
    var stock = $("#stock").val();
    var author = $("#author").val();
    var resultData = checkData();
    if (resultData) {
        var bookData = {
            bookName: name,
            price: price,
            stock: stock,
            author: author
        };
        var data = JSON.stringify(bookData);
        $.ajax({
            type: "POST",
            data: data,
            url: "/addBookToDatabase",
            cache: false,
            headers: {"Content-Type": "application/json;charset=utf-8"},
            success: function (data) {
                var result = data;
                if (result == "success") {
                    alert("新增成功");
                }
                if (result == "fail") {
                    alert("新增失败");
                }
            },
            error: function (err) {
                alert("服务器出错");
            }
        });
    }
}

//新增前内容检查
function checkData() {
    var name = $("#bookName").val();
    var price = $("#price").val();
    var stock = $("#stock").val();
    var author = $("#author").val();
    var priceReg = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
    var stockReg = /^\+?[1-9][0-9]*$/;
    if (name == "") {
        alert("书名不能为空");
        return false;
    }
    if (price != "") {
        if (!priceReg.test(price)) {
            alert("请输入正确的产品价格：整数或者保留两位小数")
            return false;
        }
    } else {
        alert("价格不能为空");
        return false;
    }
    if (stock != "") {
        if (!stockReg.test(stock)) {
            alert("请输入正确的库存数量：整数")
            return false;
        }
    } else {
        alert("库存数量不能为空");
        return false;
    }
    if (author == "") {
        alert("书名不能为空");
        return false;
    }
    return true;
}