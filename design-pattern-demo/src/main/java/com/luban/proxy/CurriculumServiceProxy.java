package com.luban.proxy;

import com.luban.entity.Curriculumn;
// 代理要表现得“就像是”真实实现类，所以需要实现CurriculumService
public class CurriculumServiceProxy implements CurriculumService {
    // 内部一定要有一个真实的实现类，当然也可以通过构造方法注入
    private CurriculumService curriculumService = new CurriculumServiceImpl();
    public Curriculumn makePublicCurriculum() {
        System.out.println("我们马上要创建公开课了");
        Curriculumn curriculumn = curriculumService.makePublicCurriculum();
        //可以做一些增强操作
        System.out.println("公开课创建完成了");
        curriculumn.setPrice(1);
        return curriculumn;
    }

    public Curriculumn makeInsideCurriculum() {
        System.out.println("我们马上要创建内部课了");
        Curriculumn curriculumn = curriculumService.makeInsideCurriculum();
        //可以做一些增强操作
        System.out.println("内部课创建完成了");
        return curriculumn;
    }
}
