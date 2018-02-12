package com.sh.pri.commons;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/2/12.
 */
public class CookieUtils {

    /**
     * 检索所有的cookie,封装到map
     * @param request
     * @return cookieMap
     */
    public static Map<String, Object> readAllCookies(HttpServletRequest request){
        Map<String, Object> cookieMap = new HashMap<String, Object>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies){
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }

    /**
     * 通过cookie中的name,获取对应的value
     * @param name
     * @return value
     */
    public static String getCookieValueByName(HttpServletRequest request, String name){

        Map<String, Object> cookieMap = readAllCookies(request);
        if (null != cookieMap && cookieMap.containsKey(name)){
            String value = String.valueOf(cookieMap.get(name));
            return value;
        }else {
            return null;
        }
    }
}
