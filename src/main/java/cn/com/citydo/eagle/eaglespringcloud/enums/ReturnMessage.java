package cn.com.citydo.eagle.eaglespringcloud.enums;

/**
 * Created by Caorenpeng
 * 2019/05/17
 */
public enum ReturnMessage {
    SUCCESS(200, "成功"),
    SYSTEM_ERROR(101, "服务器开小差了..."),
    RESULT_NOT_EXISTS(1, "结果不存在"),
    SIGN_ERROR(102,"签名错误"),
    DATA_EXISTS(103,"数据已存在,无需重复添加"),
    PARAMS_NOT_EXISTS(2, "请求参数缺省,请核对");

    private String msg;

    private Integer code;

    ReturnMessage(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
