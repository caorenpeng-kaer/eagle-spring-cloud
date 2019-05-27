package cn.com.citydo.eagle.eaglespringcloud.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * <h1>JSON与Bean之间的相互转换</h1>
 * Created by Caorenpeng
 * 2019/5/20
 */
public class JsonBeanUtil {
    /**
     * <h2>JSON对象转化为实体类</h2>
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(JSONObject json, Class clazz) {
        return (T) JSON.toJavaObject(json, clazz);
    }

    /**
     * <h2>json数组转对象实体列表</h2>
     *
     * @param array
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> json2BeanList(JSONArray array, Class<T> clazz) {
        return JSON.parseArray(array.toString(),clazz);
    }

    /**
     * <h2>实体类转为JSON</h2>
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> JSONObject bean2Json(T t) {
        JSONObject jo = (JSONObject) JSON.toJSON(t);
        return jo;
    }

    /**
     * <h2>实体类转为Map</h2>
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map bean2Map(T t) {
        Map map = (Map) JSON.toJSON(t);
        return map;
    }
}
