package com.scorer.clientPhone.watch;

import java.io.OutputStream;

public class WatchOutputInfo {
    private String Key;
    private String DeviceInfo;
    private OutputStream watchOutputStream;

    public WatchOutputInfo(String key, String deviceInfo, OutputStream watchOutputStream) {
        Key = key;
        DeviceInfo = deviceInfo;
        this.watchOutputStream = watchOutputStream;
    }

    public String getKey() {
        return Key;
    }

    public String getDeviceInfo() {
        return DeviceInfo;
    }

    public OutputStream getWatchOutputStream() {
        return watchOutputStream;
    }

}
