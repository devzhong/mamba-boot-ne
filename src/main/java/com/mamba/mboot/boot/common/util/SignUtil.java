package com.mamba.mboot.boot.common.util;

import com.mamba.mboot.boot.common.ThreadContext;

public class SignUtil {
    public SignUtil() {
    }

    public static String getAppId() {
        return (String) ThreadContext.get("appId");
    }
}