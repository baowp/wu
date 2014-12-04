package com.wubole.fight.service.impl;

import com.wubole.fight.entity.ExperienceEntity;
import com.wubole.fight.persistence.ExperienceMapper;
import com.wubole.fight.service.ExperienceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by baowp on 14-12-3.
 */
@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Resource
    private ExperienceMapper experienceMapper;
    @Override
    public int insert(ExperienceEntity entity) {
        return experienceMapper.insert(entity);
    }

    @Override
    public ExperienceEntity get(long id) {
        return experienceMapper.get(id);
    }
}
