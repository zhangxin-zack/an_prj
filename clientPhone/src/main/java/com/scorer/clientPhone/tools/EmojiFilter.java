package com.scorer.clientPhone.tools;

import org.apache.commons.lang.StringUtils;

public class EmojiFilter {

    public static String FilterEmoji(String source,String slipStr) {
        if(StringUtils.isNotBlank(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        }else{
            return source;
        }
    }

    public static String FilterEmoji(String source) {
        if(StringUtils.isNotBlank(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]","");
        }else{
            return source;
        }
    }
}
