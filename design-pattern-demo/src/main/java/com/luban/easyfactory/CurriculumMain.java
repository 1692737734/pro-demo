package com.luban.easyfactory;

public class CurriculumMain {
    public static void main(String[] args){
        Curriculumn curriculumn = CurriculumFactory.makeCurriculum("public");
        System.out.println(curriculumn.getDesc());
    }
}
