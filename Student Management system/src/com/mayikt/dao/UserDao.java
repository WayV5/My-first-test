package com.mayikt.dao;

import com.mayikt.entity.StudentEntity;
import com.mayikt.entity.UserEntity;
import com.mayikt.service.UserService;
import com.mayikt.utils.MayiktJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    /**
     * 用户注册
     * 如果使用相同手机号码注册。。。。。。提示已经存在
     */
    public int registerUser(UserEntity userEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            String insertStudentSql = "INSERT INTO `newdb`.`mayikt_users` (`id`, `phone`, `pwd`) VALUES (null, "
                    + userEntity.getPhone() + ", " + userEntity.getPwd() + ")";
            System.out.println("insertStudentSql:" + insertStudentSql);
            preparedStatement = connection.prepareStatement(insertStudentSql);
            // log输出
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            MayiktJdbcUtils.closeConnection(preparedStatement, connection);
        }
    }

    /**
     * 根据手机号码查询用户信息
     *
     * @param userPhone
     * @return
     */
    public UserEntity getByPhoneUser(String userPhone) {
        if (userPhone == null || userPhone.length() == 0) {
            return null;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            String getByPhoneUserSql = "select  * from mayikt_users where phone=" + userPhone;
            preparedStatement = connection.prepareStatement(getByPhoneUserSql);
            System.out.println("getByPhoneUserSql: " + getByPhoneUserSql);
            resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            if (!result) {
                return null;
            }
            Long id = resultSet.getLong(1);
            String phone = resultSet.getString(2);
            String pwd = resultSet.getString(3);
            // 将db中查询到数据封装成java学生对象
            return new UserEntity(id, phone, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            MayiktJdbcUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public UserEntity login(UserEntity userEntity){
        if (userEntity == null ) {
            return null;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            String loginSql = "SELECT * FROM `mayikt_users` WHERE phone= "+userEntity.getPhone()+" and pwd = '"+userEntity.getPwd()+"'";  //有漏洞
            preparedStatement = connection.prepareStatement(loginSql);
            System.out.println("loginSql: " + loginSql);
            resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            if (!result) {
                return null;
            }
            Long id = resultSet.getLong(1);
            String phone = resultSet.getString(2);
            String pwd = resultSet.getString(3);
            // 将db中查询到数据封装成java学生对象
            return new UserEntity(id, phone, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            MayiktJdbcUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public UserEntity login2(UserEntity userEntity){
        if (userEntity == null ) {
            return null;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            String loginSql = "SELECT * FROM `mayikt_users` WHERE phone= ? and pwd = ?";
            preparedStatement = connection.prepareStatement(loginSql);
            preparedStatement.setString(1,userEntity.getPhone());
            preparedStatement.setString(2,userEntity.getPwd());
            System.out.println("loginSql: " + loginSql);
            resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            if (!result) {
                return null;
            }
            Long id = resultSet.getLong(1);
            String phone = resultSet.getString(2);
            String pwd = resultSet.getString(3);
            // 将db中查询到数据封装成java学生对象
            return new UserEntity(id, phone, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            MayiktJdbcUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }
}
