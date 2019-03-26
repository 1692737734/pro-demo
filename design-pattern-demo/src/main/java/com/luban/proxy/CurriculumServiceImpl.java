package com.luban.proxy;

import com.luban.entity.Curriculumn;
import com.luban.entity.InsideCurriculum;
import com.luban.entity.PublicCurriculum;

public class CurriculumServiceImpl implements CurriculumService {
    public Curriculumn makePublicCurriculum() {
        Curriculumn curriculumn = new PublicCurriculum();
        curriculumn.setDesc("公开课");
        return curriculumn;
    }

    public Curriculumn makeInsideCurriculum() {
        Curriculumn curriculumn = new InsideCurriculum();
        curriculumn.setDesc("内部课");
        return curriculumn;
    }
}
