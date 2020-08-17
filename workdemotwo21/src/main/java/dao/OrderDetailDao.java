package dao;

import bean.OrderDetailBean;
import util.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailDao {
    /**
     * 保存订单详细信息
     * */
    public boolean saveOrderDetail(OrderDetailBean detailBean){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "insert into order_detail (bookId, bookName, price, number, countBook, orderId) values(?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, detailBean.getBookId());
            ps.setString(2, detailBean.getBookName());
            ps.setDouble(3, detailBean.getPrice());
            ps.setInt(4, detailBean.getNumber());
            ps.setDouble(5, detailBean.getCountBook());
            ps.setString(6, detailBean.getOrderId());
            ps.executeUpdate();
            conn.commit();
            System.out.println("saveOrderDetail：数据提交成功");
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

}
