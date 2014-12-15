package com.wubole.fight.utils.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author baowp
 */
public class ClientSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(ClientSupport.class);

    static JSON getJSONResult(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            if (logger.isInfoEnabled()) {
                logger.info("response statusCode is not 200: "
                        + response.getStatusLine());
            }
        }
        StringBuilder sbResult = getPrimaryResult(response);

        JSON json = (JSON) JSON.parse(sbResult.toString());
        return json;
    }

    static StringBuilder getPrimaryResult(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        try {
            InputStream instream = entity.getContent();
            InputStreamReader reader = new InputStreamReader(instream);
            StringBuilder sb = new StringBuilder();
            int c;
            try {
                while ((c = reader.read()) > -1)
                    sb.append((char) c);
            } finally {
                reader.close();
            }
            if (sb.length() == 0 || sb.charAt(0) != '{' && sb.charAt(0) != '[') {
                if (logger.isInfoEnabled()) {
                    logger.info("response is not json: " + sb);
                }
            }
            return sb;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    static HttpResponse clientExecute(HttpClient httpclient,
                                      HttpUriRequest httpUriRequest) {
        HttpResponse response = null;
        byte retry = 1;
        do {
            try {
                response = httpclient.execute(httpUriRequest);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        } while (response == null && retry-- > 0);
        return response;
    }
}
