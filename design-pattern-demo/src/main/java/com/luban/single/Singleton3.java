package com.luban.single;

/**
 * 嵌套类的实现
 */
public class Singleton3 {
    private Singleton3() {
        System.out.println("初始化");
    }

    private static class Holder{
        private static Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance(){
        return Holder.instance;
    }

}
