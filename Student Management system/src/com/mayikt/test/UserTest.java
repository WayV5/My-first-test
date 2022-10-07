package com.mayikt.test;

import com.mayikt.entity.UserEntity;
import com.mayikt.service.UserService;

import java.util.Scanner;

public class UserTest {
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        index();
    }

    public static void index(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入数字1：用户注册");
        System.out.println("输入数字2：用户登录");
        int i = scanner.nextInt();
        switch (i){
            case 1:
                registerUser();
                break;
            case 2:
                login();
                break;
        }
    }
    public static void registerUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入注册用户手机号");
        String phone = scanner.next();
        System.out.println("请输入注册用户密码");
        String pwd = scanner.next();
        int i = userService.register(new UserEntity(phone, pwd));
        if(i>0){
            System.out.println("注册成功");
        }else {
            System.out.println("注册失败");
        }
    }

    public static void login(){
        //给用户输入账号密码错误的机会  3次
        for(int i = 0; i<3;i++){
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入注册用户手机号");
            String phone = scanner.nextLine();
            System.out.println("请输入注册用户密码");
            String pwd = scanner.nextLine();
            UserEntity user = userService.login2(new UserEntity(phone, pwd));
            if(user !=null){
                System.out.println("登陆成功");
                return;//  直接结束整个方法   ，break结束最近层的循环
            }else {
                System.out.println("用户输入的手机号码或者密码不正确！ 你还有" + (2-i)+ "次机会");
            }
        }
        System.out.println("三次错误 没有机会了 结束了拜拜");
    }
}
