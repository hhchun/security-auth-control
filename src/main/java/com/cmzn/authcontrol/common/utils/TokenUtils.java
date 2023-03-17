package com.cmzn.authcontrol.common.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.DES;

public class TokenUtils {
    private static final String SECRET_KEY = "AUTHCONTROL";
    private static final DES DES = new DES(Mode.CTS, Padding.ZeroPadding, SECRET_KEY.getBytes());

    public static String getToken(String userId) {
        if (userId == null) {
            return null;
        }
        String iv = RandomUtil.randomString(8);
        DES.setIv(iv.getBytes());
        return DES.encryptHex(userId) + "." + iv;
    }

    public static String getUserId(String token){
        String[] split = token.split("\\.");
        if (split.length != 2){
            return null;
        }
        DES.setIv(split[1].getBytes());
        return DES.decryptStr(split[0]);
    }
}
