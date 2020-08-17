//当前页数pageNumber
var pageNumber = 1;
//每一页条数数size
var size = 5;

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
                    + '<td>' + "<input type='text' id='number' name='number'>" + '</td>'
                    + '<td>' + "<button type='button' id='addCart' name='addCart' onclick='addCart(this)'>加入购物车</button>" + '</td></tr>');
            }
        },
        error: function (err) {
            alert("服务器出错");
        }
    });
    $("#book_id").hide();//--隐藏id栏
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
