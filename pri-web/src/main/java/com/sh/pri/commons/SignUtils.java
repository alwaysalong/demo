package com.sh.pri.commons;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 * 验签工具类
 *
 */
@SuppressWarnings("all")
public class SignUtils {

    private static Log log = LogFactory.getLog(SignUtils.class);

    private static final Logger logger = Logger.getLogger(SignUtils.class);

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    public static void main(String[] args) {
        Map<String,Object> signMap = new HashMap<String, Object>();
        signMap.put("a", "A002");
        signMap.put("b", "PC");
        signMap.put("c", "uleClearing");
        String sign = sign(signMap, "45130f2ed3ee2a08194c0a955ae76b01");
        System.out.println(sign);
    }
    public static String sign(Object obj, String key) {
        StringBuffer param = new StringBuffer();
        List msgList = objToList(obj);
        Collections.sort(msgList);
        for (int i = 0; i < msgList.size(); i++) {
            String msg = (String) msgList.get(i);
            if (i > 0) {
                param.append("&");
            }
            param.append(msg);
        }
        param.append("&key=" + key);
        logger.info("singStr===》》》》》》》》》》" + param.toString());
        String signStr = signStr(param.toString());
        return signStr;
    }

    public static boolean verify(Object obj, String key, String sign) {
        List msgList = objToList(obj);
        Collections.sort(msgList);
        StringBuffer param = new StringBuffer();
        for (int i = 0; i < msgList.size(); i++) {
            String msg = (String) msgList.get(i);
            if (i > 0) {
                param.append("&");
            }
            param.append(msg);
        }
        param.append("&key=" + key);
        String signStr = signStr(param.toString());
        logger.info("verify sign: orgSign=[" + sign + "],targetSign=[" + signStr + "]");
        return signStr.equals(sign);
    }

    private static String signStr(String needSignStr) {
        String resultString = null;
        try {
            resultString = needSignStr;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
        return resultString;
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b
     *            字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     *
     * @param b
     *            要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static List objToList(Object obj) {
        List msgList = new ArrayList();
        // Map
        if (obj instanceof java.util.Map) {
            Map<String, Object> fieldMap = (Map<String, Object>) obj;
            Set<String> keySet = fieldMap.keySet();
            for (String key : keySet) {
                if ("sign".equalsIgnoreCase(key)) {
                    continue;
                }
                Object fieldVal = fieldMap.get(key);
                if (null != fieldVal && !"".equals(fieldVal + "")) {
                    msgList.add(key + "=" + (fieldVal + ""));
                }
            }
            return msgList;
        }
        // Class
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            // if (field.isAnnotationPresent(Property.class)) {
            String fieldName = field.getName();
            if ("sign".equalsIgnoreCase(fieldName)) {
                continue;
            }
            String getMethodName = "get" + toFirstLetterUpperCase(field.getName());
            try {
                Object fieldVal = obj.getClass().getMethod(getMethodName).invoke(obj);
                if (null != fieldVal && !"".equals(fieldVal + "")) {
                    msgList.add(fieldName + "=" + (fieldVal + ""));
                }
            } catch (Exception e) {
                log.info(e.getMessage(), e);
            }
        }
        return msgList;
    }

    private static String toFirstLetterUpperCase(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        String firstLetter = str.substring(0, 1).toUpperCase();
        return firstLetter + str.substring(1, str.length());
    }
}
