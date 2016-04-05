package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.*;
import com.shop.itis.service.*;
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
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    GoodService goodService;

    @Autowired
    CartService cartService;

    @Autowired
    AddressService addressService;

    @Autowired
    OrderService orderService;

    @CategoryMenu
    @RequestMapping(method = RequestMethod.GET)
    public String page(ModelMap map) {
        map.put("user", Utils.getAutentificationUser(userService));
        map.put(Constants.ATTR_ADDRESS_FORM, new Address());
        return "pages/order";
    }

    @RequestMapping("/delete/good")
    public String deleteGood(@RequestParam("goodId") Long goodId) {
        Good deleteGood = goodService.getGoodById(goodId);

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

        User user = Utils.getAutentificationUser(userService);

        List<UserGoods> userGoodses = cartService.getUserAllGoods(user.getUsername());
        Double sum = 0.0;
        for (UserGoods userGoods : userGoodses) {
            sum += userGoods.getGood().getPrice() * userGoods.getCount();
        }

        addressService.update(address);

        Order order = new Order(user, address, new Date(), sum, "to check", "webMoney");
        orderService.add(order);


        user.getAddress().add(address);
        user.getOrders().add(order);
        userService.update(user);

        return "redirect:/catalog";
    }
}
