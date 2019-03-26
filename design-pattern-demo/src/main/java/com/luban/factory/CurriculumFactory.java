package com.luban.factory;

import com.luban.entity.Curriculumn;

/**
 * 课程工厂接口
 */
public interface CurriculumFactory {
    Curriculumn makeCurriculumn(String name);
}
