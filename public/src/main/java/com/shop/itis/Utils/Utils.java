package com.shop.itis.Utils;

import com.shop.itis.domain.GoodWrapper;
import com.shop.itis.domain.User;
import com.shop.itis.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class Utils {
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

    public static String getEmailText(String email) {
        return Constants.EMAIL_TEXT + email.hashCode();
    }


    public static Integer getAttributeCartGoodsCount(HttpServletRequest servletRequest) {
        return (Integer) servletRequest.getSession().getAttribute(Constants.CART_GOODS_COUNT);
    }

    public static Double getAttrSum(HttpServletRequest servletRequest) {
        return (Double) servletRequest.getSession().getAttribute(Constants.CART_SUM);
    }

    public static Set<GoodWrapper> getAttributeCartGoods(HttpServletRequest servletRequest) {
        return (Set<GoodWrapper>) servletRequest.getSession().getAttribute(Constants.CART_GOODS);
    }


    public static void addAttributes(Set<GoodWrapper> goods, Double sum, int size, HttpServletRequest servletRequest) {
        servletRequest.getSession().setAttribute(Constants.CART_SUM, sum);
        servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, size);
        servletRequest.getSession().setAttribute(Constants.CART_GOODS, goods);
    }
}
