package cn.com.citydo.eagle.eaglespringcloud.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Caorenpeng
 * 2018/9/30
 */
public class SysResult<T> implements Serializable {
    private boolean success;

    private Object msg;

    private Object data;

    public static final boolean TRUE = true;

    public static final boolean FALSE = false;

    private Integer code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static SysResult ok() {
        SysResult sysResult = new SysResult();
        sysResult.setSuccess(TRUE);
        sysResult.setCode(200);
        sysResult.setMsg("成功");
        sysResult.setData(new HashMap<>());
        return sysResult;
    }

    public static <T> SysResult ok(T data) {
        SysResult sysResult = new SysResult();
        sysResult.setSuccess(TRUE);
        sysResult.setCode(200);
        sysResult.setMsg("成功");
        Class<?> clazz = data.getClass();
        Map map = new HashMap<>();
        if (clazz == int.class || clazz == Integer.class) {
            map.put("int", data);
            sysResult.setData(map);
        } else if (clazz == String.class) {
            map.put("string", data);
            sysResult.setData(map);
        } else if (clazz == ArrayList.class || clazz == List.class) {
            map.put("list", data);
            sysResult.setData(map);
        } else if (clazz == Map.class || clazz == HashMap.class) {
            sysResult.setData(data);
        } else if (clazz == boolean.class) {
            map.put("boolean", data);
            sysResult.setData(map);
        } else if (clazz == double.class || clazz == Double.class) {
            map.put("double", data);
            sysResult.setData(map);
        } else if (clazz == long.class || clazz == Long.class) {
            map.put("long", data);
            sysResult.setData(map);
        } else {
            sysResult.setData(data);
        }
        return sysResult;
    }

    public static SysResult fail(Integer code, String msg) {
        SysResult sysResult = new SysResult();
        sysResult.setSuccess(FALSE);
        sysResult.setCode(code);
        sysResult.setMsg(msg);
        sysResult.setData(new HashMap<>());
        return sysResult;
    }

    public static SysResult fail(String msg) {
        SysResult sysResult = new SysResult();
        sysResult.setSuccess(FALSE);
        sysResult.setCode(500);
        sysResult.setMsg(msg);
        sysResult.setData(new HashMap<>());
        return sysResult;
    }

    @Override
    public String toString() {
        return "SysResult{" +
                "success=" + success +
                ", msg=" + msg +
                ", data=" + data +
                ", code=" + code +
                '}';
    }
}
