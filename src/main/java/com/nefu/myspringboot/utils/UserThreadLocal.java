package com.nefu.myspringboot.utils;

import com.nefu.myspringboot.entity.User;

public class UserThreadLocal {
    //无参构造函数
    private UserThreadLocal() {
    }

    //ThreadLocal 变量
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    //将user放入本地ThreadLocal中
    public static void put(User user) {
        LOCAL.set(user);
    }

    //将本地ThreadLocal中的user移除
    public static void remove() {
        LOCAL.remove();
    }
    //获取本地ThreaLocal的user
    public static User get() {
        return LOCAL.get();
    }
}
