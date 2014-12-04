package com.wubole.fight.service;

import com.wubole.fight.entity.ExperienceEntity;

/**
 * Created by baowp on 14-12-3.
 */
public interface ExperienceService {

    int insert(ExperienceEntity entity);

    ExperienceEntity get(long id);
}
