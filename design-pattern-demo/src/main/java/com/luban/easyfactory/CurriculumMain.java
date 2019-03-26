package com.luban.easyfactory;

import com.luban.entity.Curriculumn;

public class CurriculumMain {
    public static void main(String[] args){
        Curriculumn curriculumn = CurriculumFactory.makeCurriculum("public");
        System.out.println(curriculumn.getDesc());
    }
}
