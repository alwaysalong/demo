package com.sh.pri.commons;

import org.apache.log4j.Logger;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 工具类
 */
public class JsonUtil {

    private static Logger logger = Logger.getLogger(JsonUtil.class);

    public static void responseMessage(String payResult) {
        PrintWriter out = null;
//        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            out = response.getWriter();
            out.print(payResult);
        } catch (IOException e) {
            logger.error("responseMessage IOException.", e);
        } finally {
            out.close();
        }
    }

    public static void responseJsonMessage(String jsonResult, String jsonCallBack) {
        PrintWriter out = null;
//        HttpServletResponse response = ServletActionContext.getResponse();
//        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        try {
            String Origin = request.getHeader("Origin");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            // 跨域
            response.setHeader("Access-Control-Allow-Headers", "accept, content-type, x-requested-with");
            response.setHeader("Access-Control-Allow-Method", "POST");
            response.setHeader("Access-Control-Allow-Origin", Origin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            out = response.getWriter();
            if (jsonCallBack == null) {
                out.print(jsonResult);
            } else {
                out.print(jsonCallBack + "(" + jsonResult + ")");
            }
        } catch (IOException e) {
            logger.error("responseJsonMessage IOException jsonResult" + jsonResult + ",IOException:", e);
        } finally {
            out.close();
        }
    }

    /**
     * bean 转 Map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object obj) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        if (obj == null) {
            throw new Exception("beanToMap param is null.");
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            fields[j].setAccessible(true);
            try {
                data.put(fields[j].getName(), fields[j].get(obj) == null ? "" : fields[j].get(obj).toString());
            } catch (Exception e) {
                logger.error("beanToMap Exception:", e);
                throw new Exception("beanToMap param is error.");
            }
        }
        data.remove("serialVersionUID");
        return data;
    }

    public static String paserJson(Object obj, boolean flag) {
        JSONObject jsonObj = null;
        if (obj != null) {
            jsonObj = JSONObject.fromObject(obj);
        } else {
            jsonObj = new JSONObject();
        }
        jsonObj.put("success", flag);
        return jsonObj.toString();
    }

    public static Map<String, String> toMap(String key, String... values) {
        if (org.apache.commons.lang.StringUtils.isBlank(key)) {
            return null;
        }
        String[] keys = key.split(",");
        Map<String, String> map = new HashMap<String, String>(keys.length);
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    /**
     * @Title: getObjectFromJsonString
     * @param @param jsonString
     * @param @param pojoCalss
     * @param @return 设定文件
     * @return Object 返回类型
     * @throws
     */
    public static Object getObjectFromJsonString(String jsonString, Class pojoCalss) {
        try {
            JSONObject jsonObject = JSONObject.fromObject(jsonString);
            Object pojo = JSONObject.toBean(jsonObject, pojoCalss);
            return pojo;
        } catch (Exception e) {
            logger.error("getJsonStringFromObject Exception", e);
        }
        return null;
    }

    /**
     * @Title: getJsonStringFromObject
     * @param @param object
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getJsonStringFromObject(Object object) {
        try {
            JSONObject json = JSONObject.fromObject(object);
            return json.toString();
        } catch (Exception e) {
            logger.error("getJsonStringFromObject Exception", e);
        }
        return null;
    }

    /**
     * @Title: getJsonStringFromMap
     * @param @param map
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getJsonStringFromMap(Map map) {
        try {
            JSONObject json = JSONObject.fromObject(map);
            return json.toString();
        } catch (Exception e) {
            logger.error("getJsonStringFromMap Exception", e);
        }
        return null;
    }

//    public static String getIP(HttpServletRequest request) {
//        try {
//            String ip = request.getHeader("x-forwarded-for");
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//                ip = request.getHeader("Proxy-Client-IP");
//            }
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//                ip = request.getHeader("WL-Proxy-Client-IP");
//            }
//            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//                ip = com.web.util.Tools.getIpAddr(request);
//            }
//            return ip;
//        } catch (Exception e) {
//            logger.error("getIP Exception", e);
//        }
//        return null;
//    }

    /**
     * 解析参数
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJsonMap(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object obj : json.keySet()) {
            Object value = json.get(obj);
            map.put(obj.toString(), value);
        }
        return map;
    }
}

