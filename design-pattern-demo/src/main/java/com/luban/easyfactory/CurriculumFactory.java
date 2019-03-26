package com.luban.easyfactory;

/**
 * 简单工厂模式
 * 课程工厂
 */
public class CurriculumFactory {
    public static Curriculumn makeCurriculum(String name){
        if(name.equals("public")){
            Curriculumn curriculumn = new PublicCurriculum();
            curriculumn.setDesc("公开课");
            return curriculumn;
        }
        if(name.equals("inside")){
            Curriculumn curriculumn = new InsideCurriculum();
            curriculumn.setDesc("内部课");
            return curriculumn;
        }
        return  null;
    }
}
