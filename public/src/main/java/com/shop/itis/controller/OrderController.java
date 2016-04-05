package com.shop.itis.controller;

import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.*;
import com.shop.itis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
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
    @RequestMapping
    public String page(ModelMap map) {
        map.put("user", Utils.getAutentificationUser(userService));
        return "pages/order";
    }

    @RequestMapping("/delete/good")
    public String deleteGood(@RequestParam("goodId") Long goodId) {
        Good deleteGood = goodService.getGoodById(goodId);

        return "pages/order";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrder(@RequestParam("city") String city,
                           @RequestParam("street") String street,
                           @RequestParam("house") Integer house,
                           @RequestParam("flat") Integer flat,
                           @RequestParam("index") Integer index
    ) {
        User user = Utils.getAutentificationUser(userService);

        List<UserGoods> userGoodses = cartService.getUserAllGoods(user.getUsername());
        Double sum = 0.0;
        for (UserGoods userGoods : userGoodses) {
            sum += userGoods.getGood().getPrice() * userGoods.getCount();
        }

        Address address1 = new Address(city, street, house, flat, index);
        addressService.add(address1);

        Order order = new Order(user, address1, new Date(), sum, "to check", "webMoney");
        orderService.add(order);


        user.getAddress().add(address1);
        user.getOrders().add(order);
        userService.update(user);

        return "redirect:/catalog";
    }
}
