package util;
/**
 * 数据库连接
 * */
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class DataBaseConnect {
    private static Connection coon;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private PreparedStatement pst;
    private ResultSet rst;

    static {
        try{
            InputStreamReader in = new InputStreamReader(DataBaseConnect.class.getResourceAsStream("/jdbc.properties"));
            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            //加载驱动路径
            Class.forName(driver);
            System.out.println("成功加载驱动");
//            coon =DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //数据库连接（如果连接关闭则重新创建）
    public static Connection getConnection(){
        try{
            coon =DriverManager.getConnection(url,user,password);
            System.out.println("连接数据库成功！");
            return coon;
        } catch (SQLException e){
            System.out.println("连接数据库失败！");
            e.printStackTrace();
            return null;
        }
    }
    //关闭数据库（有结果集）
    public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ps != null){
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    //关闭数据库（无结果集）
    public static void close(Connection conn){
        try{
            if(conn!=null){
                conn.close();
                System.out.println("成功关闭数据库！");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void close(PreparedStatement ps){
        try{
            if(ps!=null){
                ps.close();
                System.out.println("成功关闭连接！");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String agrs[]) {
        DataBaseConnect.getConnection();
    }
}
