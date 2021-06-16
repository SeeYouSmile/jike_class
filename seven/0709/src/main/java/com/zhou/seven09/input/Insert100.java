package com.zhou.seven09.input;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Insert100 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url="jdbc:mysql://localhost:3306/baotao";
        String username="root";
        String password="";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql="insert into user (user_id,username,password,nikename,createtime,updatetime) values ";

        List<String> sqls=new ArrayList<>();

        StringBuffer sb;
        for (int i = 0; i < 100; i++) {
            sb=new StringBuffer();
            for (int j = i*10000; j < (i+1)*10000; j++) {
                String s = String.valueOf(j + 1);
                long l = System.currentTimeMillis();
                sb.append("('").append(s).append("','name").append(s).append("','password")
                        .append(s).append("','nick").append(s).append("',").append(l).append(",")
                        .append(l).append("),");
            }
            String sql2 = sql+sb.substring(0,sb.length()-1);
            sqls.add(sql2);
        }
        long start=System.currentTimeMillis()/1000;
        System.out.println("start");
        for (String sql3 : sqls) {
            System.out.println(sql3);
            connection.prepareStatement(sql3).executeUpdate();
        }
        long end=System.currentTimeMillis()/1000;
        System.out.println("end");
        System.out.println("耗时："+(end-start)+"秒");//大概46秒
    }
}
