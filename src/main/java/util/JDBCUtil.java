package util;

import java.sql.*;

/**
 *
 * 2014.07.23
 * @author zhutianpeng
 * 使用单例建立JDBC工具类
 */
public class JDBCUtil {
    private  static final  long serialVersionUID=1401604527639033631L;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://cxk:3306/wechat";
    private static String root = "root";
    private static String password = "admin";
    private static JDBCUtil jdbcUtil = null;
    private static Connection conn = null;
    private JDBCUtil(){}

    static{
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            System.out.println("【加载驱动失...】");
            e.printStackTrace();

        }
    }
    /**
     *
     * @return jdbcUtil
     *
     * 实现获取JDBCUtil工具类的对象
     */
    public static JDBCUtil getInstance(){
        if(jdbcUtil==null){
            synchronized(JDBCUtil.class){
                if(jdbcUtil==null){
                    jdbcUtil = new JDBCUtil();
                }
            }
        }
        return  jdbcUtil;
    }
    /**
     *
     * @return conn
     *
     * 获取数据库连接
     */
    public Connection getConnection(){
        try {
            conn  = DriverManager.getConnection(url,root,password);
        } catch (SQLException e) {
            System.out.println("【获取连接失败...】");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     *
     * @param rs
     * @param st
     * @param conn
     *
     * 关闭数据库连接
     */
    public void free(ResultSet rs,Statement st,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("【ResultSet关闭失败...】");
                e.printStackTrace();
            }finally{
                if(st!=null){
                    try {
                        st.close();
                    } catch (SQLException e) {
                        System.out.println("【Statement关闭失败...】");
                        e.printStackTrace();
                    }finally{
                        if(conn!=null){
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                System.out.println("【Connection关闭失败...】");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}

