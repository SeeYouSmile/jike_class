package com.zhou.seven09.input;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class Insert100 {

    final int total=1000000;
    final int ever=5000*2;

    String url="jdbc:mysql://localhost:3306/baotao?rewriteBatchedStatements=true";
    String username="root";
    String password="";

    AtomicInteger atomicInteger;

    public void input(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(total/ever);
        HikariPool hikariPool = new HikariPool(hikariConfig);

        final String sql="insert into user (user_id,username,password,nikename,createtime,updatetime) values ";

        atomicInteger=new AtomicInteger();

        long start=System.currentTimeMillis();
        for (int i = 0; i < total / ever; i++) {
            final int ii=i;
            StringBuffer stringBuffer = new StringBuffer(sql);
            Runnable runnable = () -> {
                for (int j = ii*ever; j < (ii + 1) * ever; j++) {
                    long l=System.currentTimeMillis();
                    stringBuffer.append("('").append(j+1).append("','name_").append(j+1).append("','password_")
                            .append(j+1).append("','nick_").append(j+1).append("',").append(l).append(",")
                            .append(l).append("),");
                }
                String sql_go = stringBuffer.substring(0, stringBuffer.length() - 1);
                try {
                    hikariPool.getConnection().prepareStatement(sql_go).executeUpdate();
                    int n = atomicInteger.incrementAndGet();
                    System.out.println("finish-->"+n);
                    if(n==total/ever){
                        long end=System.currentTimeMillis();
                        System.out.println((end-start)/1000+" seconds");
                        System.exit(0);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            };
            runnable.run();
        }
    }

    public static void main(String[] args) {
        Insert100 insert100 = new Insert100();
        insert100.input();
    }
}
