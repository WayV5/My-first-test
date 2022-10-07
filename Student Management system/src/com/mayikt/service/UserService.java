package com.mayikt.service;

import com.mayikt.dao.UserDao;
import com.mayikt.dao.UserDao2;
import com.mayikt.entity.UserEntity;

public class UserService {
    private UserDao userDao = new UserDao();
    private UserDao2 userDao2 = new UserDao2();
    public int register(UserEntity userEntity){
        String phone = userEntity.getPhone();
        UserEntity dbUser = userDao.getByPhoneUser(phone);
        if(dbUser!=null){ //如果不是空 表示之前存在
            System.out.println("该用户手机号码已经存在~！");
            return -1;
        }

        //用户之前不存在 直接注册
        return userDao.registerUser(userEntity);
    }

    public UserEntity login(UserEntity userEntity){
        return userDao.login(userEntity);
    }

    public UserEntity login2(UserEntity userEntity){
        return userDao.login2(userEntity);
    }
}
