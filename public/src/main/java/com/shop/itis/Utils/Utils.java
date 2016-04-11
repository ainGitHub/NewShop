package com.shop.itis.Utils;

import com.shop.itis.domain.Cart;
import com.shop.itis.domain.UserInfo;
import com.shop.itis.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Utils {
    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }

    public static UserInfo getAutentificationUser(UserService userService) {
        User user = null;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

    public static List<Cart> getAttributeCartGoods(HttpServletRequest servletRequest) {
        return (List<Cart>) servletRequest.getSession().getAttribute(Constants.CART_GOODS);
    }


    public static void addAttributes(List<Cart> goods, Double sum, int size, HttpServletRequest servletRequest) {
        servletRequest.getSession().setAttribute(Constants.CART_SUM, sum);
        servletRequest.getSession().setAttribute(Constants.CART_GOODS_COUNT, size);
        servletRequest.getSession().setAttribute(Constants.CART_GOODS, goods);
    }
}
