package cn.it.jdbc;

import java.sql.*;

public class jdbcDemo3 {
    public static void main(String[] args) {
        Statement stmt=null;
        Connection conn=null;
        ResultSet rs=null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3","root","password");
//            String sql="insert into account (id,name,balance) values('4','陈','1234')";
//            String sql="update account set balance = 1500 where id='3' ";
//            String sql="delete from account where id='4'";
            String sql="select * from account";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //游标向下移动一行
            rs.next();
            //获取数据
            int id = rs.getInt(1);
            String name = rs.getString("name");
            String balance=rs.getString("balance");
            System.out.println(id+"——"+name+"——"+balance);


//            if (count>0){
//                System.out.println("执行成功!");
//            }else {
//                System.out.println("执行失败！");
//            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
