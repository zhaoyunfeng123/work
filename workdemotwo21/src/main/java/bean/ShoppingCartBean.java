package bean;

public class ShoppingCartBean {
    //ID
    private Integer cartId;
    //用户名
    private String userName;
    //图书id
    private String bookId;
    //图书名称
    private String bookName;
    //图书单价
    private Double price;
    //购买数量
    private Integer number;
    //金额
    private Double count;

    @Override
    public String toString() {
        return "ShoppingCartBean{" +
                "cartId=" + cartId +
                ", userName='" + userName + '\'' +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", count=" + count +
                '}';
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

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
}
