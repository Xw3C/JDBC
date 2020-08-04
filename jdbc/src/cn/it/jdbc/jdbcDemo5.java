package cn.it.jdbc;

import cn.it.domain.emp;
import cn.it.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbcDemo5 {
    public static void main(String[] args) {
        List<emp> list = new jdbcDemo5().findAll2();
        System.out.println(list);

    }
    /**
     * 查询所有emp对象
     * @return
     */


    public List<emp> findAll(){
        Connection conn=null;
        ResultSet rs=null;
        Statement stmt=null;
        List<emp> list = null;
        try {
            //1.注册驱动
//            Class.forName("com.mysql.jdbc.Driver");
//            //2.获取连接
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/db3", "root", "password");

            conn=JDBCUtils.getConnection();
            //3、定义sql语句
            String sql="select * from account";
            //4、获取执行sql对象
            stmt = conn.createStatement();
            //5、执行sql语句
            rs=stmt.executeQuery(sql);
            //6、遍历结果集，封装对象，转载集合
            emp emp=null;
            list=new ArrayList<emp>();
            while(rs.next()){
                //获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String balance=rs.getString("balance");
                //创建emp对象
                emp=new emp();
                emp.setId(id);
                emp.setName(name);
                emp.setBalance(balance);
                list.add(emp);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            if (rs!=null){
//                try {
//                    rs.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            if (stmt!=null){
//                try {
//                   stmt.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            if (conn!=null){
//                try {
//                    conn.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
            JDBCUtils.close(rs,stmt,conn);
        }

        return list;
    }


    public List<emp> findAll2(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<emp> list = null;
        try {
           /* //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql:///db3", "root", "root");*/
            conn = JDBCUtils.getConnection();
            //3.定义sql
            String sql = "select * from account";
            //4.获取执行sql的对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.遍历结果集，封装对象，装载集合
            emp emp = null;
            list = new ArrayList<emp>();
            while(rs.next()){
                //获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String balance=rs.getString("balance");
                //创建emp对象
                emp=new emp();
                emp.setId(id);
                emp.setName(name);
                emp.setBalance(balance);
                list.add(emp);

                //装载集合
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            /*if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*/

            JDBCUtils.close(rs,stmt,conn);
        }
        return list;
    }
}
