package cn.it.jdbc;

import cn.it.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

//登录验证
public class jdbcDemo6LoginTest {
    public static void main(String[] args) {
        //1、键盘录入
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password= sc.nextLine();
        //2、调用方法
        boolean flag = new jdbcDemo6LoginTest().login2(username, password);

        //3、判断结果，输出不同语句
        if (flag){
            System.out.println("登录成功");
        }else {
            System.out.println("用户名或密码错误");
        }
    }

    public boolean login(String username,String password){

        if (username == null || password == null){
            return false;
        }
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            //1、获取连接
            conn= JDBCUtils.getConnection();
            //2、定义sql语句
            String sql="select * from user where username = '"+username+"'and password = '"+password+"'";
            //3、获取执行sql的对象
            stmt = conn.createStatement();
            //4、执行sql语句
            rs = stmt.executeQuery(sql);
            //5、判断
            return rs.next();//如果有下一行，放回true


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(rs,stmt,conn);

        }


        return false;

    }



    //使用PreparedStatement实现
    public boolean login2(String username,String password){

        if (username == null || password == null){
            return false;
        }
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            //1、获取数据库连接对象
            conn= JDBCUtils.getConnection();
            //2、定义sql语句
            String sql="select * from user where username = ? and password = ?";
            //3、通过连接对象创建预编译对象
            pstmt = conn.prepareStatement(sql);
            //给?赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //4、执行sql语句
            rs = pstmt.executeQuery();
            //5、判断
            return rs.next();//如果有下一行，放回true


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);

        }


        return false;

    }

}
