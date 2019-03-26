package com.luban.proxy;

import com.luban.entity.Curriculumn;

public interface CurriculumService {
    Curriculumn makePublicCurriculum();
    Curriculumn makeInsideCurriculum();
}
