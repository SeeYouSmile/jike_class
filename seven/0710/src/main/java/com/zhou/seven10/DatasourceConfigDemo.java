package com.zhou.seven10;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatasourceConfigDemo {

    public static void main(String[] args) throws SQLException {
        DatasourceConfigDemo datasourceConfigDemo = new DatasourceConfigDemo();
        DataSource dataSource = datasourceConfigDemo.dataSource();
        Connection connection = dataSource.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into t_user (id,name,age) values (2,'gg',17)");
//        int i = preparedStatement.executeUpdate();
//        System.out.println(i);

        //使用id查询，会根据id%2的结果，实际只去db_1查询结果
        PreparedStatement preparedStatement1 = connection.prepareStatement("select * from t_user where id=1");
        ResultSet resultSet = preparedStatement1.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while(resultSet.next()){
            for (int j = 0; j < columnCount; j++) {
                String columnName = metaData.getColumnName(j+1);
                Object value = resultSet.getObject(columnName);
                System.out.print(columnName+":"+value+"|");
            }
            System.out.println();
        }
    }

    public DataSource dataSource() throws SQLException {
        Map<String,DataSource> dataSourceMap=new HashMap<>();
        HikariDataSource dataSource1=new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/db_0");
        dataSource1.setUsername("root");
        dataSource1.setPassword("");
        dataSourceMap.put("db_0",dataSource1);
        HikariDataSource dataSource2=new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://localhost:3306/db_1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("");
        dataSourceMap.put("db_1",dataSource2);

        //分库分表范围
        ShardingTableRuleConfiguration shardingTableRuleConfiguration=new ShardingTableRuleConfiguration("t_user","db_$->{0..1}.t_user_$->{0..1}");
        //分库规则
        shardingTableRuleConfiguration.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("id","dbRule"));
        //分表规则
        shardingTableRuleConfiguration.setTableShardingStrategy(new StandardShardingStrategyConfiguration("age","tableRule"));

        ShardingRuleConfiguration shardingRuleConfiguration=new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTables().add(shardingTableRuleConfiguration);

        Properties dbProps=new Properties();
        //分库算法
        dbProps.setProperty("algorithm-expression","db_$->{id % 2}");
        shardingRuleConfiguration.getShardingAlgorithms().put("dbRule",new ShardingSphereAlgorithmConfiguration("INLINE",dbProps));

        Properties tableProps=new Properties();
        //分表算法
        tableProps.setProperty("algorithm-expression","t_user_$->{age % 2}");
        shardingRuleConfiguration.getShardingAlgorithms().put("tableRule",new ShardingSphereAlgorithmConfiguration("INLINE",tableProps));

        Properties properties = new Properties();
        //打印sql
        properties.setProperty("sql-show","true");
        DataSource dataSource= ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfiguration),properties);
        return dataSource;
    }
}
