package com.wubole.fight.utils.protocol;

import com.wubole.fight.utils.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
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
    private String uid="rayburn";
    private String pwd="232323";
    private String md5= DigestUtils.md5Hex(pwd+uid);

    @Test
    public void testGet() throws URISyntaxException {
        String url = "http://api.sms.cn/mm/?";
        url += "uid="+uid+"&pwd="+md5;
        String result = normalHttpClient.get(new URI(url));
        assertNotNull(result);
    }

    @Test
    public void testSend() throws URISyntaxException {
        String url = "http://api.sms.cn/mt/?";
        String content= StringUtils.encode("您的验证码是123456。请在页面中提交验证码完成验证。");
        url += "uid="+uid+"&pwd="+md5+"&mobile=18658875027&content="+content+"&encode=utf8";
        String result = normalHttpClient.get(new URI(url));
        assertNotNull(result);
    }
}
