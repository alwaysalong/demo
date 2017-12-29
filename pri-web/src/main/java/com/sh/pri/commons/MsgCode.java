package com.sh.pri.commons;

import com.sh.pri.exceptions.PriException;

/**
 * Created by admin on 2017/12/29.
 * msg信息提示枚举类
 */
@SuppressWarnings("all")
public enum  MsgCode {
    SYSTEM_SUCCES("0000", "操作成功"),
    ERR_DEFAULT_PARAM("1000", "传入参数不能为空"),
    SYSTEM_ERR("9999", "系统异常");

    private String code;
    private String msg;

    private MsgCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private String toMsg(String fieldName) {
        return "" + msg + "," + fieldName + "为必填项!";
    }

    private String toJson() {
        return "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "!\"}";
    }

    private String toReturnJson() {
        return "{\"code\":\"" + code + "\",\"returnMsg\":\"" + msg + "!\"}";
    }

    public PriException toPriException() throws PriException {
        throw new PriException(toJson());
    }

    public PriException returnPriException() throws PriException {
        throw new PriException(toReturnJson());
    }

    public static void toPriException(String code) throws PriException {
        for (MsgCode mc : values()) {
            if (mc.code.equals(code)) {
                throw new PriException(mc.toJson());
            }
        }
    }

    public static void toPriException(String code, String fieldName) throws PriException {
        for (MsgCode mc : values()) {
            if (mc.code.equals(code)) {
                throw new PriException(mc.toMsg(fieldName));
            }
        }
    }

    public static String getByCode(String code) {
        for (MsgCode mc : values()) {
            if (mc.code.equals(code)) {
                return mc.msg;
            }
        }
        return null;
    }
}
