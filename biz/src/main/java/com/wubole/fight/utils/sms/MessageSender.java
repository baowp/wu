package com.wubole.fight.utils.sms;

import com.wubole.fight.utils.StringUtils;
import com.wubole.fight.utils.protocol.NormalHttpClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by baowp on 14-12-20.
 */
@Component
public class MessageSender {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private NormalHttpClient normalHttpClient;
    private String uid = "rayburn";
    private String pwd = "232323";
    private String md5 = DigestUtils.md5Hex(pwd + uid);

    private String url = "http://api.sms.cn/mt/?";

    public String sendValidateCode(String code, String mobile) {
        String content = StringUtils.encode("您的短信验证码为" + code + "，请在页面中提交请求。【五伯乐】");
        //content = StringUtils.encode("您的验证码是" + code + "。请在页面中提交验证码完成验证。【优安鲜品】");
        url += "uid=" + uid + "&pwd=" + md5 + "&mobile=" + mobile + "&content=" + content + "&encode=utf8";
        String result = null;
        try {
            result = normalHttpClient.get(new URI(url));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        if (logger.isInfoEnabled()) {
            logger.info("sms sent message returns: {}", result);
        }
        return result;
    }
}
