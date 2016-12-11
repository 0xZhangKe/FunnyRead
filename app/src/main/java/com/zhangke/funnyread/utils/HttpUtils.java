package com.zhangke.funnyread.utils;

import com.google.gson.Gson;

/**
 * Created by ZhangKe at 2016/12/11
 */
public class HttpUtils {
    //将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }
}
