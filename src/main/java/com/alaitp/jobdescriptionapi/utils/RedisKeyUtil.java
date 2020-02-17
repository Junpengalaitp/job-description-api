package com.alaitp.jobdescriptionapi.utils;

public class RedisKeyUtil {

    public static String getKeyWithColumn(String tableName,String majorKey,String majorKeyValue,String column) {
        return tableName + ":" + majorKey + ":" + majorKeyValue + ":" + column;
    }

    public static String getKey(String tableName,String majorKey,String majorKeyValue){
        return tableName + ":" + majorKey + ":" + majorKeyValue + ":";
    }
}