package dao;

import bean.UserBean;
import util.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    /**
     * 注册
     * */
    public boolean save(UserBean user){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "insert into user (userName, password, fullName, userType, tel, email, address, city, country, hobby, problem, answer, createTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFullName());
            ps.setString(4,user.getUserType());
            ps.setString(5,user.getTel());
            ps.setString(6,user.getEmail());
            ps.setString(7,user.getAddress());
            ps.setString(8,user.getCity());
            ps.setString(9,user.getCountry());
            ps.setString(10,user.getHobby());
            ps.setString(11,user.getProblem());
            ps.setString(12,user.getAnswer());
            ps.setString(13,user.getCreateTime());
            ps.executeUpdate();
            conn.commit();
            System.out.println("数据提交成功");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            try{
                conn.rollback();
                System.out.println("数据提交失败");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            return false;
        }finally {
            try{
                ps.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    /**
     * 登录
     * */
    public UserBean findByUserNameAndPasswordAndUserType(String userName, String password, String userType){
        Connection con = DataBaseConnect.getConnection();
        String sql = "SELECT * FROM user WHERE userName=? AND password=? AND userType=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, userType);
            rs = ps.executeQuery();
            UserBean user = new UserBean();
            while (rs.next()){
                String name = rs.getString("userName");
                String pwd = rs.getString("password");
                String type = rs.getString("userType");
                user.setUserName(name);
                user.setPassword(pwd);
                user.setUserType(type);
            }
            con.commit();
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }finally {
            try{
                ps.close();
                rs.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    /**
     * 根据用户名查找用户
     * */
    public UserBean findByUserName(String userName){
        Connection con = DataBaseConnect.getConnection();
        String sql = "SELECT * FROM user WHERE userName=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            UserBean user = null;
            while (rs.next()){
                String name = rs.getString("userName");
                user = new UserBean();
                user.setUserName(name);
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullName"));
                user.setTel(rs.getString("tel"));
                user.setEmail(rs.getString("email"));
                user.setHobby(rs.getString("hobby"));
                user.setProblem(rs.getString("problem"));
                user.setAnswer(rs.getString("answer"));
            }
            con.commit();
            return user;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }finally {
            try{
                ps.close();
                rs.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    /**
     * 查询所有用户
     * */
    public List<UserBean> findAllUser(){
        Connection con = DataBaseConnect.getConnection();
        String sql = "SELECT * FROM user";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            UserBean userBean = null;
            List<UserBean> list = new ArrayList<>();
            while (rs.next()) {
                userBean = new UserBean();
                userBean.setUserName(rs.getString("userName"));
                userBean.setPassword(rs.getString("password"));
                userBean.setFullName(rs.getString("fullName"));
                userBean.setTel(rs.getString("tel"));
                userBean.setEmail(rs.getString("email"));
                userBean.setHobby(rs.getString("hobby"));
                userBean.setProblem(rs.getString("problem"));
                userBean.setAnswer(rs.getString("answer"));
                list.add(userBean);
            }
            con.commit();
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }finally {
            try{
                ps.close();
                rs.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    /**
     * 删除用户
     * */
    public boolean removeUser(String userName){
        Connection con = DataBaseConnect.getConnection();
        String sql = "delete from user where userName = ?";
        PreparedStatement ps = null;
        try{
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1,userName);

            ps.executeUpdate();
            con.commit();
            System.out.println("removeUser:数据提交成功");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            try {
                con.rollback();
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
     * 修改用户信息
     * */
    public boolean updateUser(UserBean user){
        Connection conn = DataBaseConnect.getConnection();
        String sql = "update user set password=?, fullName=?, tel=?, email=?, hobby=?, problem=?, answer=?, modifiedTime=? where userName=?";
        PreparedStatement ps = null;
        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getPassword());
            ps.setString(2,user.getFullName());
            ps.setString(3,user.getTel());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getHobby());
            ps.setString(6,user.getProblem());
            ps.setString(7,user.getAnswer());
            ps.setString(8,user.getModifiedTime());
            ps.setString(9,user.getUserName());
            ps.executeUpdate();
            conn.commit();
            System.out.println("数据提交成功");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            try{
                conn.rollback();
                System.out.println("数据提交失败");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            return false;
        }finally {
            try{
                ps.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }



    }





}
