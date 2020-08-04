package cn.it.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcDemo2 {
    public static void main(String[] args) {
        Statement stmt=null;
        Connection conn=null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3","root","password");
//            String sql="insert into account (id,name,balance) values('4','陈','1234')";
//            String sql="update account set balance = 1500 where id='3' ";
//            String sql="delete from account where id='4'";
            String sql="create table student (id int,name varchar (255))";

            stmt = conn.createStatement();
            int count=stmt.executeUpdate(sql);
            System.out.println(count);
//            if (count>0){
//                System.out.println("执行成功!");
//            }else {
//                System.out.println("执行失败！");
//            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
