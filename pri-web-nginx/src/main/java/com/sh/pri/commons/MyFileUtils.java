package com.sh.pri.commons;

import java.io.IOException;
import java.net.URLEncoder;

import sun.misc.BASE64Encoder;

public class MyFileUtils {
    
    /**
     * 下载文件时，针对不同浏览器，进行附件名的编码
     * 
     * @param filename
     *            下载文件名
     * @param agent
     *            客户端浏览器
     * @return 编码后的下载附件名
     * @throws IOException
     */
    public static String encodeDownloadFilename(String fileName, String agent) throws IOException {
            if (agent.contains("Firefox")) { // 火狐浏览器
                    fileName = "=?UTF-8?B?" + new BASE64Encoder().encode(fileName.getBytes("utf-8")) + "?=";
                    fileName = fileName.replaceAll("\r\n", "");
            } else { // IE及其他浏览器
                    fileName = URLEncoder.encode(fileName, "utf-8");
                    fileName = fileName.replace("+", " ");
            }
            return fileName;
    }
}
