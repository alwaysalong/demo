package com.sh.pri.commons;

import org.apache.log4j.Logger;

/**
 * Created by admin on 2018/2/12.
 */
public class Tools {

    private static Logger log = Logger.getLogger(Tools.class);
    /**
     * 转string,如果为空,返回空串
     * @param
     * @return
     */
    public static String ts(Object a){
        String str = "";
        try {
            if (null != a){
               str = String.valueOf(a);
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(ts(""));
    }
}
