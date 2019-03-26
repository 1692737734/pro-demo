package com.luban.single;

public class SingletonMain {
    public static void main(String[] args){
        System.out.println("开始");
        Singleton3 singleton3 = Singleton3.getInstance();
        Singleton3 singleton31 = Singleton3.getInstance();
    }
}
