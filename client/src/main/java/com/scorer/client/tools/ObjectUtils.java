package com.scorer.client.tools;

public class ObjectUtils {

    public static boolean isEmpty(Object test){
        return test == null || "".equals(test) || "".equals(test + "") || "null".equalsIgnoreCase(test + "") || "null".equals(test) || test == "";
    }

    public static boolean noneEmpty(Object... tests){
        for(Object test:tests){
            if(test == null || "".equals(test) || "".equals(test + "") || "null".equalsIgnoreCase(test + "") || "null".equals(test) || test == "")
                return false;
        }
        return true;
    }

}
