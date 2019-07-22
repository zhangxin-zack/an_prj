package com.scorer.tools;

import java.util.UUID;

public class EncryptHead {

    public String str;
    public String enStr;

    public EncryptHead() {
        str = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        enStr = EncryptTool.encodeScorerSC(str);
    }
}
