package com.luban.factory;

import com.luban.entity.Curriculumn;
import com.luban.entity.InsideCurriculumA;
import com.luban.entity.InsideCurriculumB;

/**
 * 内部课工厂
 */
public class InsideCurriculumFactory implements CurriculumFactory {
    public Curriculumn makeCurriculumn(String name) {
        if (name.equals("A")) {
            Curriculumn curriculumn =  new InsideCurriculumA();
            curriculumn.setDesc("内部课A");
            return curriculumn;
        } else if (name.equals("B")) {
            Curriculumn curriculumn =  new InsideCurriculumB();
            curriculumn.setDesc("内部课B");
            return curriculumn;
        } else {
            return null;
        }
    }
}
