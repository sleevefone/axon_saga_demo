package com.saga.example.axon.utils;

import java.util.UUID;

/**
 * @author fanhb on 2021/9/8
 *
 */
public class IdUtils {
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
