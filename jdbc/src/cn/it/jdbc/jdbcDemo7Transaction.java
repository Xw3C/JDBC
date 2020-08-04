package cn.it.jdbc;

import cn.it.util.JDBCUtils;
import com.sun.org.apache.xpath.internal.objects.XNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcDemo7Transaction {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            //1、获取数据库连接对象
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //2、定义sql语句

            String sql1 = "update account set balance =balance - ? where id = ? ";
            String sql2 = "update account set balance =balance + ? where id = ? ";
            //3、获取执行sql语句的预编译对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //4、设置参数
            pstmt1.setDouble(1,500);
            pstmt1.setInt(2,1);

            pstmt2.setDouble(1,500);
            pstmt2.setInt(2,2);
            //5、执行sql语句
            pstmt1.executeUpdate();
            int i = 3/0;
            pstmt2.executeUpdate();

            //提交事务
            conn.commit();


        } catch (Exception e) {
            //事务回滚
            try {
                if (conn!=null){
                conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt1,conn);
            JDBCUtils.close(pstmt2,null);
        }
    }

}
