package com.luban.factory;

import com.luban.entity.Curriculumn;
import com.luban.entity.PublicCurriculumA;
import com.luban.entity.PublicCurriculumB;

/**
 * 公开课工厂
 */
public class PublicCurriculumFactory implements CurriculumFactory {
    public Curriculumn makeCurriculumn(String name) {
        if (name.equals("A")) {
            Curriculumn curriculumn =  new PublicCurriculumA();
            curriculumn.setDesc("公开课A");
            return curriculumn;
        } else if (name.equals("B")) {
            Curriculumn curriculumn =  new PublicCurriculumB();
            curriculumn.setDesc("公开课B");
            return curriculumn;
        } else {
            return null;
        }

    }
}
