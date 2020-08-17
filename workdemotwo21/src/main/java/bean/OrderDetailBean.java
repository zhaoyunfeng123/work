package bean;

public class OrderDetailBean {
    //id
    private Integer id;
    //图书id
    private String bookId;
    //图书名称
    private String bookName;
    //图书价格
    private Double price;
    //图书数量（库存）
    private Integer number;
    //每个图书n本的总价
    private Double countBook;
    //订单id
    private String orderId;

    @Override
    public String toString() {
        return "OrderDetailBean{" +
                "id=" + id +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", countBook=" + countBook +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getCountBook() {
        return countBook;
    }

    public void setCountBook(Double countBook) {
        this.countBook = countBook;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
