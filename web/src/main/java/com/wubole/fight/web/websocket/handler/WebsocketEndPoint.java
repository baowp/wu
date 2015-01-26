package com.wubole.fight.web.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by baowp on 2015/1/22.
 */
@Component("websocket")
public class WebsocketEndPoint extends TextWebSocketHandler{

    private final Logger logger= LoggerFactory.getLogger(getClass());
    private ScheduledExecutorService executor= Executors.newScheduledThreadPool(2);

    @Override
    protected void handleTextMessage(final WebSocketSession session,
                                     TextMessage message) throws Exception {
        logger.info("handleTextMessage");
        super.handleTextMessage(session, message);
        final TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("run scheduleAtFixedRate sendMessage");
                    session.sendMessage(returnMessage);
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        },0,1000, TimeUnit.MILLISECONDS);
        //session.sendMessage(returnMessage);
    }
}
