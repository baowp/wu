package com.wubole.fight.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by baowp on 14-12-20.
 */
public class NormalTest {

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Test
    public void test(){
        Random r=new Random();
        logger.info(String.valueOf(r.nextInt(999999)));
    }
}
