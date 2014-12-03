package com.wubole.fight.service;

import com.wubole.fight.entity.ExperienceEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

/**
 * Created by baowp on 14-12-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-context.xml"})
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class ExperienceServiceTest {

    @Resource
    private ExperienceService experienceService;

    @Test
    public void testInsert() {
        ExperienceEntity entity = new ExperienceEntity();
        int flag = experienceService.insert(entity);
        assertTrue(flag > 0);
    }
}
