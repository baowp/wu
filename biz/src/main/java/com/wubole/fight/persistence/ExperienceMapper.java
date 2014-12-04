package com.wubole.fight.persistence;

import com.wubole.fight.entity.ExperienceEntity;

/**
 * Created by baowp on 14-12-3.
 */
public interface ExperienceMapper {

    int insert(ExperienceEntity experience);

    ExperienceEntity get(long id);

}
