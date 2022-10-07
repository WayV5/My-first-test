package com.mayikt.demo;

import java.sql.*;

public class JdbcDemo02 {
    public static final String SQL1 = "show databases like 'newdb';";
    public static final String SQL2 = "create database newdb;";
    public static final String SQL3 = "create table students(" +
            "id int not null auto_increment, " +
            "name varchar(10) not null, " +
            "age int ," +
            "primary key(id)" +
            ");";
    public static final String SQL4 = "insert into students values(null,'红中',18),(null,'白板',18)";
    public static final String SQL5 = "delete from students where name = '北风'";
    public static final String SQL6 = "update students set name = 'wtf' where id=1";
    public static final String SQL7 = "select name as 名字,id as 哈哈最大年龄 from students order by age";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb?serverTimezone=UTC", "root", "root");
        PreparedStatement preparedStatement = connection.prepareStatement(SQL7);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("数据库存在");
        }
        while (resultSet.next()){
            System.out.print(resultSet.getString(1)+" - ");
            System.out.println(resultSet.getString(2));
        }
    }
}
