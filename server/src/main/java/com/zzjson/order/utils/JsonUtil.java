package com.zzjson.order.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 rollBall team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.zzjson.order.utils</li>
 * <li>Version     : 1.0.0</li>
 * <li>Creation    : 2018年10月16日</li>
 * <li>@author     : zzy0_0</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
public class JsonUtil {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static Object fromJson(String string, Class classType) {
        try {
            return objectMapper.readValue(string, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json转对象
     *
     * @param string
     * @param typeReference
     * @return
     */
    public static Object fromJson(String string, TypeReference typeReference) {
        try {
            return objectMapper.readValue(string,typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}