//当前页数pageNumber
var pageNumber = 1;
//每一页条数数size
var size = 8;
//跳转页数
function page_click(item) {
    //获取点击项的id
    var pageItemId = $(item).attr("id");
    //首页
    if (pageItemId == "firstPage") {
        pageNumber = parseInt($("#currentPage").attr("value"));
        if (pageNumber != 1) {
            listAllBook(1, size);
            $("#currentPage").attr("value", 1);
        } else {
            $("#firstPage").attr("disabled", false);
        }
    }
    //上一页
    if (pageItemId == "beforePage") {
        pageNumber = parseInt($("#currentPage").attr("value"));
        if (pageNumber > 1) {
            listAllBook(pageNumber - 1, size);
            $("#currentPage").attr("value", pageNumber - 1);
        } else {
            $("#beforePage").attr("disabled", false);
        }
    }
    //指定页数跳转
    if (pageItemId == "jumpPage") {
        var jumPageNumber = $("#jumpPageValue").val();
        console.log(jumPageNumber);
        if ("" == jumPageNumber || null == jumPageNumber) {    //不输入，直接点击跳转，有bug
            alert("请输入具体页数")
            listAllBook(pageNumber, size);
            $("#currentPage").attr("value", pageNumber);
        }
        var totalNumber = $("#totalPage").val();
        if (jumPageNumber > totalNumber && jumPageNumber < 1) {
            alert("请输入正确的页数")
            listAllBook(pageNumber, size);
            $("#currentPage").attr("value", pageNumber);
        } else {
            listAllBook(jumPageNumber, size);
            $("#currentPage").attr("value", jumPageNumber);
        }
    }
    //下一页
    if (pageItemId == "nextPage") {
        pageNumber = parseInt($("#currentPage").attr("value"));
        var totalNumber = $("#totalPage").val();
        if (pageNumber < totalNumber) {
            listAllBook(pageNumber + 1, size);
            $("#currentPage").attr("value", pageNumber + 1);
        } else {
            $("#nextPage").attr("disabled", false);
        }
    }
    //尾页
    if (pageItemId == "lastPage") {
        pageNumber = parseInt($("#currentPage").attr("value"));
        var totalNumber = $("#totalPage").val();
        if (pageNumber != totalNumber) {
            listAllBook(totalNumber, size);
            $("#currentPage").attr("value", totalNumber);
        } else {
            $("#totalPage").attr("disabled", false);
        }
    }
}
//查询所有图书
function listAllBook(page, size) {
    var paramdata = {
        currentPage: page,
        pageSize: size
    }
    var selectdata = JSON.stringify(paramdata);
    $.ajax({
        type: "post",
        data: selectdata,
        url: "listAllBook",
        cache: false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            //总页数
            var total = data.totalPage;
            $("#totalPage").attr("value", total);
            $("#books tbody").empty();
            var bookdata = data.listBook;
            for (var i = 0; i < bookdata.length; i++) {
                var book = bookdata[i];
                $("#books tbody").append('<tr>'
                    + "<td name='book_id' style='display: none' class='book_id'>" + book.bookId + "</td>"
                    + '<td name=\'book_name\'>' + book.bookName + '</td>'
                    + '<td name=\'book_price\'>' + book.price + '</td>'
                    + '<td name=\'book_stock\'>' + book.stock + '</td>'
                    + '<td name=\'book_author\'>' + book.author + '</td>'
                    + '<td>' + "<input type='button' id='updateBook' value='更新' onclick='updateBookByName(this)'>" + "&nbsp;&nbsp;"
                    + "<input type='button' id='deleteBook' value='删除'onclick='deleteBookByName(this)'>" + '</td></tr>');
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
    $("#book_id").hide();//--隐藏
}

//更新图书
function updateBookByName(item) {
    //获取tr元素
    var n = $(item).parent().parent();
    //获取bookId
    var id = $(n).children("td[name='book_id']").text();
    //获取书名
    var name = $(n).children("td[name='book_name']").text();
    //获取价格
    var price = $(n).children("td[name='book_price']").text();
    //获取库存
    var stock = $(n).children("td[name='book_stock']").text();
    //获取作者
    var author = $(n).children("td[name='book_author']").text();
    $("#div_update").empty();
    $("#div_update").append("<br/><table>"
        + "<tr><td>书名</td><td name='book_update_name'>" + "<input type='text' id='update_name' name='update_name' value='" + name + "'>" + "</td></tr>"
        + "<tr><td>价格</td><td name='book_update_price'>" + "<input type='text' id='update_price' name='update_price' value='" + price + "'>" + "</td></tr>"
        + "<tr><td>库存</td><td name='book_update_stock'>" + "<input type='text' id='update_stock' name='update_stock' value='" + stock + "'>" + "</td></tr>"
        + "<tr><td>作者</td><td name='book_update_author'>" + "<input type='text' id='update_author' name='update_author' value='" + author + "'>" + "</td></tr>"
        + "</table>"
        + "<br/>"
        + "<button type='button' id='updateToBooks' name='updateToBooks'>更新</button>"
    );
    $("#updateToBooks").click(function () {
        updateToBooks(n);
    });
}
//更新
function updateToBooks(info) {
    //获取更新内容
    var update_id = $(info).children("td[name='book_id']").text();
    var update_name = $("#update_name").val();
    var update_price = $("#update_price").val();
    var update_stock = $("#update_stock").val();
    var update_author = $("#update_author").val();
    var book = {
        bookId:update_id,
        bookName:update_name,
        price:update_price,
        stock:update_stock,
        author:update_author
    };
    var data = JSON.stringify(book);
    $.ajax({
        type: "post",
        data: data,
        url: "/updateBook",
        cache: false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            var result = data;
            if (result == "success") {
                alert("更新成功");
                //更新内容
                //更新书名
                $(info).children("td[name='book_name']").text(update_name);
                //更新价格
                $(info).children("td[name='book_price']").text(update_price);
                //更新库存
                $(info).children("td[name='book_stock']").text(update_stock);
                //更新作者
                $(info).children("td[name='book_author']").text(update_author);
            }
            if (result == "fail") {
                alert("更新失败");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}
//删除图书
function deleteBookByName(item) {
    //获取父类td元素的值
    var n = $(item).parent().parent();
    //获取id
    var id = $(n).children("td[name='book_id']").text();
    var book = {
        bookId: id
    };
    var data = JSON.stringify(book);
    $.ajax({
        type: "post",
        data: data,
        url: "/deleteBook",
        cache: false,
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (data) {
            var result = data;
            if (result == "success") {
                alert("删除成功");
                listAllBook(1, size);
            }
            if (result == "fail") {
                alert("删除失败");
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
}