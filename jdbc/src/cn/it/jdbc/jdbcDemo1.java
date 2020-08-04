package cn.it.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class jdbcDemo1 {

    public static void main(String[] args) throws Exception {
        //1、导入驱动jar包
        //2、注册驱动 mysql5之后的驱动jar包可以不用写注册驱动，jar包下meta-inf文件下的services文件下的Driver可以帮助注册驱动
        Class. forName("com.mysql.jdbc.Driver");
        //3、获取数据库的连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "password");
        //4、定义数据库语句
        String sql="delete account ";
        //5、获取执行sql对象 Statement
        Statement stmt = conn.createStatement();
        //6、执行sql语句  执行DML(insert、update、delete)语句、ddL(create、alter、drop)语句
        int count = stmt.executeUpdate(sql);
        //7、处理结果 返回值是受到影响的行数，可以判断是否被正确执行，
        System.out.println(count);
        //8、释放资源
        stmt.close();
        conn.close();


    }
}
