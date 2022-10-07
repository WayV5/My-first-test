package com.mayikt.service;

import com.mayikt.dao.StudentDao;
import com.mayikt.entity.StudentEntity;
import java.util.ArrayList;


public class StudentService {

    /**
     * new 学生对象dao层
     */
    private StudentDao studentDao = new StudentDao();

    /**
     * 查询所有的学生信息
     *
     * @return
     */
    public ArrayList<StudentEntity> allStudent() {
        // 在通过业务逻辑层调用dao层代码
        ArrayList<StudentEntity> studentEntities = studentDao.allStudent();
        return studentEntities;
    }

    public StudentEntity getByIdStudent(Integer stuId) {
        return studentDao.getByIdStudent(stuId);
    }

    public int insertStudent(StudentEntity stu) {
        return studentDao.insertStudent(stu);
    }

    public int updateStudent(StudentEntity stu) {
        return studentDao.updateStudent(stu);
    }

    public int delStudent(Integer id) {
        return studentDao.delStudent(id);
    }

}
