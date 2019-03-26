package com.luban.build;

/**
 * 用户类
 */
public class User {

    // 下面是“一堆”的属性
    private String name;
    private String password;
    private String nickName;
    private int age;
    //私有化构造方法
    private User(String name, String password, String nickName, int age){
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
    }
    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder{
        private String name;
        private String password;
        private String nickName;
        private int age;

        private UserBuilder(){}
        //链式调用各个属性值，返回this
        public UserBuilder name(String name){
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }
        //build方法负责将UserBuilder中的属性复制到User
        //在复制的同时需要做一些检验
        public User build(){
            if (name == null || password == null) {
                throw new RuntimeException("用户名和密码必填");
            }
            if (age <= 0 || age >= 150) {
                throw new RuntimeException("年龄不合法");
            }
            // 还可以做赋予”默认值“的功能
            if (nickName == null) {
                nickName = name;
            }
            return new User(name, password, nickName, age);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                '}';
    }
}
