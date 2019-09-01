package com.scorer.feign.tools;

public class TestObject {

    public static boolean isEmpty(Object test) {
        return test == null || "".equals(test) || "".equals(test + "") || "null".equalsIgnoreCase(test + "") || "null".equals(test) || test == "";
    }

    public static boolean noneEmpty(Object... tests) {
        for (Object test : tests) {
            if (test == null || "".equals(test) || "".equals(test + "") || "null".equalsIgnoreCase(test + "") || "null".equals(test) || test == "")
                return false;
        }
        return true;
    }


    public static boolean isPositive(Integer test) {
        return test != null && test > 0;
    }

    public static boolean isPositive(Long test) {
        return test != null && test > 0;
    }

    public static boolean isPositive(Float test) {
        return test != null && test > 0;
    }

    public static boolean isPositive(Double test) {
        return test != null && test > 0;
    }
}
