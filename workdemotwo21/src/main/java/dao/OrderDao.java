package dao;

import bean.OrderBean;
import bean.OrderDetailBean;
import util.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao {
    /**
     * 保存订单
     */
    public boolean saveOrder(OrderBean orderBean) {
        Connection conn = DataBaseConnect.getConnection();
        String sql = "insert into order_entity (orderId, userName, address, count, createCartTime) values(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, orderBean.getOrderId());
            ps.setString(2, orderBean.getUserName());
            ps.setString(3, orderBean.getAddress());
            ps.setDouble(4, orderBean.getCount());
            ps.setString(5, orderBean.getCreateCartTime());
            ps.executeUpdate();
            conn.commit();
            System.out.println("saveOrder：数据提交成功");
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
     * 查询我的订单
     */
    public List<OrderBean> findAllOrder(String userName) {
        Connection conn = DataBaseConnect.getConnection();
        String sql = "select * from order_entity left join order_detail " +
                "on order_entity.orderId = order_detail.orderId where userName = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderBean orderBean = null;
        OrderDetailBean detailBean = null;
        List<OrderBean> orderList = new ArrayList<>();
        List<OrderDetailBean> detailList = null;
        Map<String, Integer> map = new HashMap<>();
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                String orderId = rs.getString("orderId");
                //判断orderId是否已存在
                boolean exit = map.containsKey(orderId);
                if (!exit) {
                    //不存在，创建order对象
                    orderBean = new OrderBean();
                    detailBean = new OrderDetailBean();
                    detailList = new ArrayList<>();

                    orderBean.setOrderId(rs.getString("orderId"));
                    orderBean.setAddress(rs.getString("address"));
                    orderBean.setCount(rs.getDouble("count"));
                    orderBean.setCreateCartTime(rs.getString("createCartTime"));


                    detailBean.setBookName(rs.getString("bookName"));
                    detailBean.setPrice(rs.getDouble("price"));
                    detailBean.setNumber(rs.getInt("number"));
                    detailBean.setCountBook(rs.getDouble("countBook"));
                    //detail集合
                    detailList.add(detailBean);
                    orderBean.setOrderDetailBeanList(detailList);

                    System.out.println("orderDao111:" + orderBean);

                    //order集合
                    orderList.add(orderBean);
                    int index = orderList.size() - 1;
                    map.put(orderId, index);
                } else {
                    //已存在
                    //存在位置的索引
                    int n = map.get(orderId);
                    orderBean = orderList.get(n);
                    detailBean = new OrderDetailBean();

                    detailBean.setBookName(rs.getString("bookName"));
                    detailBean.setPrice(rs.getDouble("price"));
                    detailBean.setNumber(rs.getInt("number"));
                    detailBean.setCountBook(rs.getDouble("countBook"));
                    //向order对象中的list集合添加数据
                    orderBean.getOrderDetailBeanList().add(detailBean);

                    System.out.println("orderDao222:" + orderBean);
                }
            }
            conn.commit();
            System.out.println("findAllOrder：数据提交成功");
            System.out.println(orderList.toString());
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
