package com.wubole.fight.web.websocket.handler;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * can't use
 * Created by baowp on 2015/1/26.
 */
@Controller
public class SampleMessageController {

    @MessageMapping("/simple")
    public Object simple(@Payload TextMessage message) {
        Map<String, Object> map = new HashMap<>();
        map.put("from", message.getPayload());
        return map;
    }
}
