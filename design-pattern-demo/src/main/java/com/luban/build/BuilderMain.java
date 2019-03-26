package com.luban.build;

public class BuilderMain {
    public static void main(String[] args){
        NewUser newUser = NewUser.builder().name("zz").password("1111").build();
        System.out.println(newUser);
    }
}
