package com.mayikt.dao;

import com.mayikt.entity.UserEntity;
import com.mayikt.utils.MayiktJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao2 {
    /**
     * 用户注册
     * 如果使用相同手机号码注册。。。。。。提示已经存在
     */
    public int registerUser(UserEntity userEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            connection.setAutoCommit(false);        //false表示自动提交关闭，手动开启事务
            String insertStudentSql = "INSERT INTO `newdb`.`mayikt_users` (`id`, `phone`, `pwd`) VALUES (null, "
                    + userEntity.getPhone() + ", " + userEntity.getPwd() + ")";
            System.out.println("insertStudentSql:" + insertStudentSql);
            preparedStatement = connection.prepareStatement(insertStudentSql);
            // log输出
            int result = preparedStatement.executeUpdate();
            //模拟代码发生了bug
            int j = 1/1;
            connection.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return 0;
        } finally {
            MayiktJdbcUtils.closeConnection(preparedStatement, connection);
        }
    }


}
