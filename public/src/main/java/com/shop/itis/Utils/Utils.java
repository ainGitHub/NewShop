package com.shop.itis.Utils;

import com.shop.itis.domain.User;
import com.shop.itis.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public final static String ADMIN = "ROLE_ADMIN";
    public final static String USER = "ROLE_USER";
    public final static String GUEST = "ROLE_GUEST";
    public final static String VISITOR = "ROLE_VISITOR";


    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }

    public static User getAutentificationUser(UserService userService) {
        org.springframework.security.core.userdetails.User user = null;
        try {
            user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            return null;
        }

        if (user != null)
            return userService.getUserByUsername(user.getUsername());

        return null;
    }
}
