package com.wubole.fight.controller;

import com.alibaba.fastjson.JSON;
import com.wubole.fight.entity.ExperienceEntity;
import com.wubole.fight.service.ExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by baowp on 2014/9/22.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private HttpServletRequest request;
    @Resource
    private ExperienceService experienceService;

    @ResponseBody
    @RequestMapping(value = "{id}")
    public ExperienceEntity add(@PathVariable long id) {
        ExperienceEntity entity = experienceService.get(id);
        if (logger.isInfoEnabled()) {
            logger.info("get: " + JSON.toJSONString(entity));
        }
        return entity;
    }

    @RequestMapping(value = "/get/{id}")
    public String get(@PathVariable long id) {
        ExperienceEntity entity = experienceService.get(2L);
        request.setAttribute("experience", entity);
        return "book/list";
    }
}
