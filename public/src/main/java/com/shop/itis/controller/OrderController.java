package com.shop.itis.controller;

import com.shop.itis.MailService;
import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.*;
import com.shop.itis.service.*;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    UserService userService;

    @Autowired
    GoodService goodService;

    @Autowired
    AddressService addressService;

    @Autowired
    OrderService orderService;

    @Autowired
    MailService mailService;

    @Autowired
    CartService cartService;

    @Autowired
    Configuration freemarkerConfiguration;

    @CategoryMenu
    @RequestMapping(method = RequestMethod.GET)
    public String page(ModelMap map) {
        UserInfo userInfo = Utils.getAutentificationUser(userService);
        map.put("userInfo", userInfo);
        map.put(Constants.ATTR_ADDRESS_FORM, new Address());

        List<Address> userAddress = addressService.userAddress(userInfo);
        map.put("userAddress", userAddress);
        return "pages/order";
    }

    @RequestMapping("/delete/good")
    public String deleteGood(@RequestParam("goodId") Long goodId) {
        Good good = goodService.getGoodById(goodId);//for delete

        Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);
        if (cart == null)
            return "redirect:/catalog";

        cart.getGoodsWrapper().remove(new GoodsWrapper(good, 1, cart));
        cartService.update(cart);
        return "pages/order";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addOrder(ModelMap map,
                           @Valid @ModelAttribute(Constants.ATTR_ADDRESS_FORM) Address address,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            map.put(Constants.ATTR_ADDRESS_FORM, address);
            return "pages/order";
        }

        UserInfo userInfo = Utils.getAutentificationUser(userService);

        Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);
        if (cart == null)
            return "redirect:/cart";

        address.setUserInfo(userInfo);
        address = addressService.alreadyExist(userInfo, address);

        if (cart.getUserInfo() == null) {
            cart.setUserInfo(userInfo);
            cartService.update(cart);
        }

        Order order = new Order(address, new Date(), cart.getSum(), "Заказ на проверке", "WebMoney", cart.getGoodsCount());
        for (GoodsWrapper g : cart.getGoodsWrapper())
            order.getGoodsWrapper().add(g);
        orderService.add(order);

        userInfo.getOrders().add(order);
        userService.add(userInfo);

        return "redirect:/catalog";
    }
}
