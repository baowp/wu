package com.wubole.fight.utils.protocol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by baowp on 14-12-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-context.xml"})
public class NormalHttpClentTest {

    @Resource
    private NormalHttpClient normalHttpClient;

    @Test
    public void testGet() throws URISyntaxException {
        String url = "http://sms.sms666.cn/WebAPI/SmsAPI.asmx/GetBalance";
        url += "?user=rayburn&pwd=859580";
        String result = normalHttpClient.get(new URI(url));
        assertNotNull(result);
    }

    @Test
    public void testSend() throws URISyntaxException {
        String url = "http://sms.sms666.cn/WebAPI/SmsAPI.asmx/SendSmsExt";
        url += "?user=rayburn&pwd=859580&mobiles=18658875027&contents=这是一条测试发的短信！&chid=0&sendtime=";
        String result = normalHttpClient.get(new URI(url));
        assertNotNull(result);
    }
}
