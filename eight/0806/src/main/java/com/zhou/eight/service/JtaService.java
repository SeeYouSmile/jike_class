package com.zhou.eight.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

@Service
public class JtaService {

    @Resource
    @Qualifier("db_0")
    private DataSource db0;

    @Resource
    @Qualifier("db_1")
    private DataSource db1;

    @Transactional
    public void testInsert(){
        Connection connection_0=null;
        Connection connection_1=null;
        try {
            connection_0 = db0.getConnection();
            String sql0="insert into messiona (name,status) value (?,?)";
            PreparedStatement preparedStatement0 = connection_0.prepareStatement(sql0);
            preparedStatement0.setObject(1,"mession0");
            preparedStatement0.setObject(2,"ok");
            int i0 = preparedStatement0.executeUpdate();

            connection_1 = db1.getConnection();
            String sql1="insert into messionb (name,status) value (?,?)";
            PreparedStatement preparedStatement1 = connection_1.prepareStatement(sql1);
            preparedStatement1.setObject(1,"mession0");
            preparedStatement1.setObject(2,"fail");
            int i1 = preparedStatement1.executeUpdate();


            System.out.println(i0==1&&i1==1?"success":"fail");
            preparedStatement0.close();
            preparedStatement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection_0!=null){
                    connection_0.close();
                }
                if(connection_1!=null){
                    connection_1.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Transactional
    public void testSelect(){
        Connection connection_0=null;
        Connection connection_1=null;
        try {
            connection_0 = db0.getConnection();
            String sql0="select id,name,status from messiona";
            ResultSet resultSet0 = connection_0.createStatement().executeQuery(sql0);

            connection_1 = db1.getConnection();
            String sql1="select id,name,status from messionb";
            ResultSet resultSet1 = connection_1.createStatement().executeQuery(sql1);

            ResultSetMetaData metaData0 = resultSet0.getMetaData();
            while(resultSet0.next()){
                for (int i = 1; i <= metaData0.getColumnCount(); i++) {
                    String columnName = metaData0.getColumnName(i);
                    Object object = resultSet0.getObject(columnName);
                    System.out.print(columnName+":"+object+"|");
                }
                System.out.println();
            }
            ResultSetMetaData metaData1 = resultSet1.getMetaData();
            while(resultSet1.next()){
                for (int i = 1; i <= metaData1.getColumnCount(); i++) {
                    String columnName = metaData1.getColumnName(i);
                    Object object = resultSet1.getObject(columnName);
                    System.out.print(columnName+":"+object+"|");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection_0!=null){
                    connection_0.close();
                }
                if(connection_1!=null){
                    connection_1.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
