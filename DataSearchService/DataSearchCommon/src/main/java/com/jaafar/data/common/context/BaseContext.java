package com.jaafar.data.common.context;

import com.jaafar.data.common.constants.CommonConstants;
import com.jaafar.data.common.util.StringHelper;

import java.util.HashMap;
import java.util.Map;

public class BaseContext {
    public static ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<>();

    public static void set(String key, String value){
        Map<String, Object> map = threadLocal.get();
        if (map == null){
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null){
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserId(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return value == null ? null : value.toString();
    }

    public static String getUserName(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getName(){
        Object value = get(CommonConstants.CONTEXT_KEY_NAME);
        return StringHelper.getObjectValue(value);
    }


    public static void setUserId(String userId){
        set(CommonConstants.CONTEXT_KEY_USER_ID, userId);
    }

    public static void setUserName(String userName){
        set(CommonConstants.CONTEXT_KEY_USER_NAME, userName);
    }

    public static void setName(String name){
        set(CommonConstants.CONTEXT_KEY_NAME, name);
    }

    public static String getToken(){
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringHelper.getObjectValue(value);
    }
    public static void setToken(String token){set(CommonConstants.CONTEXT_KEY_USER_TOKEN,token);}


    public static void remove(){
        threadLocal.remove();
    }


}
