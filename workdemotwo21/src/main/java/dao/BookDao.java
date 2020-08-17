package dao;

import bean.BookBean;
import util.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    /**
     * 添加图书
     */
    public boolean saveBook(BookBean book) {
        Connection conn = DataBaseConnect.getConnection();
        String sql = "insert into book (bookID, bookName, author, publisher, price, stock, info) values(?,?,?,?,?,?,?)";
        //保存，+img
        //String sql = "insert into book (bookID, bookName, author, publisher, price, stock, info, img) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setDouble(5, book.getPrice());
            ps.setInt(6, book.getStock());
            ps.setString(7, book.getInfo());
//            ps.setString(8,book.getImg());
            ps.executeUpdate();
            conn.commit();
            System.out.println("数据提交成功");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("数据提交失败");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * 修改图书信息
     * */
    /**
     * 查寻图书信息
     */
    public List<BookBean> findAllBooks() {
        Connection conn = DataBaseConnect.getConnection();
        String sql = "SELECT * FROM book";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            BookBean bookBean = null;
            List<BookBean> bookList = new ArrayList<>();
            while (rs.next()) {
                bookBean = new BookBean();
                bookBean.setBookId(rs.getString("bookId"));
                bookBean.setBookName(rs.getString("bookName"));
                bookBean.setAuthor(rs.getString("author"));
                bookBean.setPublisher(rs.getString("publisher"));
                bookBean.setPrice(rs.getDouble("price"));
                bookBean.setStock(rs.getInt("stock"));
                bookBean.setInfo(rs.getString("info"));
                bookBean.setImg(rs.getString("img"));
                bookList.add(bookBean);
            }
            conn.commit();
            return bookList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 删除图书
     * */
    public boolean remove(String bookId){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "delete from book where bookId = ?";
        PreparedStatement ps = null;
        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,bookId);
            ps.executeUpdate();
            conn.commit();
            System.out.println("数据提交成功");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("数据提交失败");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据图书id查询图书
     * */
    public BookBean findBookById(String bookId){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "SELECT * FROM book where bookId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, bookId);
            rs = ps.executeQuery();
            BookBean book = null;
            while (rs.next()){
                book = new BookBean();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getDouble("price"));
                book.setStock(rs.getInt("stock"));
                book.setInfo(rs.getString("info"));
                book.setImg(rs.getString("img"));
            }
            conn.commit();
            return book;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
