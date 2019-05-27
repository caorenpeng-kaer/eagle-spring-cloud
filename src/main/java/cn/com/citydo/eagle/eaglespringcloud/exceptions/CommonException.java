package cn.com.citydo.eagle.eaglespringcloud.exceptions;


import cn.com.citydo.eagle.eaglespringcloud.enums.ReturnMessage;

/**
 * Created by Caorenpeng
 * 2019/5/17
 */
public class CommonException extends RuntimeException {
    private Integer code;

    private String msg;

    /**
     * 传入枚举enum 做失败的返回
     *
     * @param message
     */
    public CommonException(ReturnMessage message) {
        this.code = message.getCode();
        this.msg = message.getMsg();
    }

    public CommonException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
