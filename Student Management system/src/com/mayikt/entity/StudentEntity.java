package com.mayikt.entity;

public class StudentEntity {
    /**
    * 学生对象
    * 在java中定义数据库实体类层
    * 不建议使用基本数据类型 使用包装类
    */
    /**
    * 学生的id
    */
    private Integer id; // 默认值null
    /**
    * 学生姓名
    */
    private String name;

    /**
    * 学生年龄
    */
    private Integer age;
    /**
    * 学生的地址
    */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public StudentEntity(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}