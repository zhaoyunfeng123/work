package dao;

import bean.ShoppingCartBean;
import util.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao {
    /**
     * 添加购物车
     * */
    public boolean saveCart(ShoppingCartBean cart){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "insert into cart (userName, bookId, number, price, bookName) values(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, cart.getUserName());
            ps.setString(2, cart.getBookId());
            ps.setInt(3, cart.getNumber());
            ps.setDouble(4,cart.getPrice());
            ps.setString(5, cart.getBookName());
//            ps.setDouble(4, cart.getCount());
            ps.executeUpdate();
            conn.commit();
            System.out.println("saveCart：数据提交成功");
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
     * 根据用户名查询购物车
     * */
    public List<ShoppingCartBean> findAllCartByUserName(String userName){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "SELECT * FROM cart where userName = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            rs = ps.executeQuery();
            ShoppingCartBean shoppingCartBean = null;
            List<ShoppingCartBean> bookList = new ArrayList<>();
            while (rs.next()) {
                shoppingCartBean = new ShoppingCartBean();
                shoppingCartBean.setCartId(rs.getInt("cartId"));
                shoppingCartBean.setBookId(rs.getString("bookId"));
                shoppingCartBean.setUserName(rs.getString("userName"));
                shoppingCartBean.setNumber(rs.getInt("number"));
                shoppingCartBean.setPrice(rs.getDouble("price"));
                shoppingCartBean.setCount(rs.getDouble("count"));
                shoppingCartBean.setBookName(rs.getString("bookName"));

                System.out.println("scDAo:"+shoppingCartBean.toString());
                bookList.add(shoppingCartBean);
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
     * 清空购物车
     * */
    public boolean removeCart(String userName){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "delete from cart where userName = ?";
        PreparedStatement ps = null;
        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ps.executeUpdate();
            conn.commit();
            System.out.println("removeCart:数据提交成功");
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
     * 根据cartID和用户名删除购物车里的商品
     * */
    public boolean removeBookByCartIdAndUserName(String userName, int cartId){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "delete from cart where cartId = ? and userName = ?";
        PreparedStatement ps = null;
        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cartId);
            ps.setString(2,userName);
            ps.executeUpdate();
            conn.commit();
            System.out.println("removeBookByCartId:数据提交成功");
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



}
