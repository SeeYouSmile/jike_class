package com.zhou.five.jdbc;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class JDBCDemo1 {
    String url="jdbc:mysql://localhost:3306/test";
    String user="root";
    String password="";
    @Before
    public void before() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }
    @Test
    public void select() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String sql="select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i + 1);
                Object value = resultSet.getObject(columnName);
                System.out.print(columnName+":"+value+"|");
            }
            System.out.println();
        }
    }
    @Test
    public void update() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String sql="update user set password='321' where id=1";
        int i = statement.executeUpdate(sql);
        if(i>0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
    }
    @Test
    public void insert() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String sql="insert into user(username,password,birthday) values ('tony','123','2020-1-1')";
        int i = statement.executeUpdate(sql);
        if(i>0){
            System.out.println("新增成功");
        }else{
            System.out.println("新增失败");
        }
    }
    @Test
    public void delete() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String sql="delete from user where username='tony'";
        int i = statement.executeUpdate(sql);
        if(i>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }
}
