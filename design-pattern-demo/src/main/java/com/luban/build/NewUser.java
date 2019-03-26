package com.luban.build;

import lombok.*;
//使用Builder作为build
@Builder
//暴露get
@Getter
//暴露toString方法
@ToString
public class NewUser {
    //不能为空
    @NonNull
    private String name;
    @NonNull
    private String password;
    //设置默认值
    @Builder.Default
    private String nickName = "aaa";
    private int age;
}
