package com.mayikt.test;

import com.mayikt.entity.StudentEntity;
import com.mayikt.service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

public class IndexTest {
    public static boolean isExit;
    public static StudentService studentService = new StudentService();

    public static void main(String[] args) {

        do {
            isExit = false;
            mainMunu();
        } while (!isExit);
    }


    /**
     * 定义主菜单程序的入口
     */
    public static void mainMunu() {
        //提示语
        System.out.println("欢迎来到我们每特教育蚂蚁课堂学生管理系统");
        System.out.println("1.查询所有的学生信息");
        System.out.println("2.根据学生id查询学生信息");
        System.out.println("3.新增学生信息");
        System.out.println("4.根据学生id修改学生信息");
        System.out.println("5.根据学生id删除学生信息");
        System.out.println("666.退出系统");
        System.out.println("请选择对应序号:");

        //接收用户输入的序号
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        switch (result) {
            case 1:
                showAllstudent();
                break;
            case 2:
                getByIdStudent();
                break;
            case 3:
                insertStudent();
                break;
            case 4:
                updateStudent();
                break;
            case 5:
                delStudent();
                break;
            case 666:
                System.out.println("系统已经关闭");
                isExit = true;
        }
    }

    public static void showAllstudent() {
        System.out.println("查询到的所有学生信息：");
        ArrayList<StudentEntity> studentEntities = studentService.allStudent();
        for (StudentEntity stu : studentEntities) {
            System.out.println(stu);
        }
    }

    public static void getByIdStudent() {
        System.out.println("根据学号查询学生信息");
        System.out.println("请输入查询的学生id");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        StudentEntity student = studentService.getByIdStudent(id);
        if (student == null) {
            System.out.println("未查询到该学生");
        } else {
            System.out.println(student);
        }
    }

    public static void insertStudent() {
        System.out.println("添加学生信息");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入新增学生姓名：");
        String name = scanner.next();
        System.out.println("请输入新增学生年龄：");
        Integer age = scanner.nextInt();
        int i = studentService.insertStudent(new StudentEntity(null, name, age));
        if (i == 0) {
            System.out.println("新增失败");
        } else if (i == 1) {
            System.out.println("新增成功");
        }

    }

    public static void updateStudent() {
        System.out.println("更新学生信息");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入更新学生学号：");
        Integer id = scanner.nextInt();
        StudentEntity student = studentService.getByIdStudent(id);
        if(student==null){
            System.out.println("没有查询到该学生");
            return;
        }
        System.out.println("请输入更新学生姓名：");
        String name = scanner.next();
        System.out.println("请输入更新学生年龄：");
        Integer age = scanner.nextInt();

        int i = studentService.updateStudent(new StudentEntity(id, name, age));
        if (i == 0) {
            System.out.println("更新失败");
        } else if (i == 1) {
            System.out.println("更新成功");
        }

    }

    public static void delStudent() {
        System.out.println("删除学生信息");
        System.out.println("请输入要删除学生id");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        int i = studentService.delStudent(id);
        if (i == 0) {
            System.out.println("删除失败");
        } else if (i == 1) {
            System.out.println("删除成功");
        }
    }
}
