package cn.com.citydo.eagle.eaglespringcloud.util;

import java.util.UUID;

/**
 * Created by Caorenpeng
 * 2019/5/22
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
