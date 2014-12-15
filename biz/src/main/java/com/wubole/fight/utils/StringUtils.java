package com.wubole.fight.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: baowp
 * Date: 1/3/14
 * Time: 5:42 PM
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final Logger logger= LoggerFactory.getLogger(StringUtils.class);
    /**
     * get the node text
     *
     * @param xml
     * @param nodeName
     * @return
     */
    public static String extract(String xml, String nodeName) {
        return extract(xml, new String[]{nodeName})[0];
    }

    /**
     * get the nodes text array
     *
     * @param xml
     * @param nodeName
     * @return
     */
    public static String[] extract(String xml, String... nodeName) {
        String result[] = new String[nodeName.length];
        int i = 0;
        for (String node : nodeName) {
            String prefix = "<" + node + ">";
            String suffix = "</" + node + ">";
            int start = xml.indexOf(prefix) + prefix.length();
            int end = xml.indexOf(suffix, start);
            result[i++] = xml.substring(start, end);
        }
        return result;
    }

    /**
     * if has <nodeName> then set the value,else append <nodeName>value</nodeName> into parent node
     *
     * @param xml
     * @param parent
     * @param nodeName
     * @param value
     * @return
     */
    public static String setNode(String xml, String parent, String nodeName, String value) {
        if (xml.indexOf("<" + nodeName + ">") > -1)
            return setNodeText(xml, nodeName, value);
        else return appendNode(xml, parent, nodeName, value);
    }

    /**
     * set node(named nodeName) text into the xml
     *
     * @param xml
     * @param nodeName
     * @param value
     * @return
     */
    public static String setNodeText(String xml, String nodeName, String value) {
        String prefix = "<" + nodeName + ">";
        String suffix = "</" + nodeName + ">";
        return xml.replaceAll(prefix + ".*" + suffix, prefix + value + suffix);
    }

    /**
     * append a new node <nodeName>value</nodeName> into parent node
     *
     * @param xml
     * @param parent
     * @param nodeName
     * @param value
     * @return
     */
    public static String appendNode(String xml, String parent, String nodeName, String value) {
        String node = "<" + nodeName + ">" + value + "</" + nodeName + ">";
        StringBuilder sb = new StringBuilder(xml);
        int index = sb.indexOf("</" + parent + ">");
        sb.insert(index, node);
        return sb.toString();
    }

    /**
     * 在字符串左侧按长度填充字符
     *
     * @param s   原字符串
     * @param len 填充后的总字符串长度
     * @param c   填充字符
     * @return 填充完的字符串
     */
    public static String padleft(String s, int len, char c) {
        s = s.trim();
        if (s.length() > len)
            throw new IllegalArgumentException("invalid len " + s.length() + "/" + len);
        StringBuilder d = new StringBuilder(len);
        int fill = len - s.length();
        while (fill-- > 0)
            d.append(c);
        d.append(s);
        return d.toString();
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String firstLetterToUpper(String str) {
        char[] array = str.toCharArray();
        array[0] -= 32;
        return String.valueOf(array);
    }

    public static String serialize(Map<String,String> params){
        StringBuilder link = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null || "".equals(value.trim())) continue;
            if (!first) link.append("&");
            try {
                link.append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
            if (first) first = false;
        }
        return link.toString();
    }

    public static Map<String, String> deserialize(String args) {
        Map<String, String> map = new HashMap<String, String>();
        for (String pairs : args.split("&")) {
            String kv[] = pairs.split("=");
            map.put(kv[0], kv[1]);
        }
        return map;
    }


}

