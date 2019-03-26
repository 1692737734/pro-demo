package com.luban.proxy;

public class ProxyMain {
    public static void main(String[] args){
        //实例化代理来进行代理
        CurriculumService curriculumService = new CurriculumServiceProxy();
        curriculumService.makePublicCurriculum();
    }
}
