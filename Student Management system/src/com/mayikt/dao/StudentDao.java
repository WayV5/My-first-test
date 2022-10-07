package com.mayikt.dao;

import com.mayikt.entity.StudentEntity;
import com.mayikt.utils.MayiktJdbcUtils;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao {
    public ArrayList<StudentEntity> allStudent() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from students");
            resultSet = preparedStatement.executeQuery();
            ArrayList<StudentEntity> studentEntities = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Integer age = resultSet.getInt(3);
                studentEntities.add(new StudentEntity(id, name, age));
            }
            return studentEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            MayiktJdbcUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }

    public StudentEntity getByIdStudent(Integer stuId) {
        if (stuId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement("select  * from students where id=" + stuId);
            resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next(); // 查询不到数据 false
            if (!result) {
                return null;
            }
            Integer id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Integer age = resultSet.getInt(3);
            // 将db中查询到数据封装成java学生对象
            StudentEntity studentEntity = new StudentEntity(id, name, age);
            return studentEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            MayiktJdbcUtils.closeConnection(resultSet, preparedStatement, connection);
        }
    }


    public int insertStudent(StudentEntity stu) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            String insertStudentSql = "INSERT INTO students values(null,'" + stu.getName() + "'," + stu.getAge() + ")";
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


    public int updateStudent(StudentEntity stu) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            connection.setAutoCommit(false);
            String updateStudentSql = "update students  set name='" + stu.getName() + "' ,age=" + stu.getAge()  + " where id=" + stu.getId() + "";
            System.out.println("updateStudentSql:" + updateStudentSql);
            preparedStatement = connection.prepareStatement(updateStudentSql);
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            MayiktJdbcUtils.closeConnection(preparedStatement, connection);
        }
    }

    public int delStudent(Integer id) {
        if (id == null) {
            return 0;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MayiktJdbcUtils.getConnection();
            String delSQL = "delete from  students where id=" + id;
            System.out.println("delSql:" + delSQL);
            preparedStatement = connection.prepareStatement(delSQL);
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            MayiktJdbcUtils.closeConnection(preparedStatement, connection);
        }
    }

}

