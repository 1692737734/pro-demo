package com.luban.factory;

import com.luban.entity.Curriculumn;

public class FactoryMain {
    public static void main(String[] args){
        CurriculumFactory curriculumFactory = new PublicCurriculumFactory();
        Curriculumn curriculumn = curriculumFactory.makeCurriculumn("B");
        System.out.println(curriculumn.getDesc());
    }
}
