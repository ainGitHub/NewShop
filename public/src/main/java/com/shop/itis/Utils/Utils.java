package com.shop.itis.Utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {
    public final static String ADMIN = "ROLE_ADMIN";
    public final static String USER = "ROLE_USER";
    public final static String GUEST = "ROLE_GUEST";
    public final static String VISITOR = "ROLE_VISITOR";


    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);

        return md5Hex;
    }
}
