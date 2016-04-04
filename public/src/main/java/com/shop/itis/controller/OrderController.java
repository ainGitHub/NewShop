package com.shop.itis.controller;

import com.shop.itis.Utils.Constants;
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
        User user = Utils.getAutentificationUser(userService);
        map.put("user", user);
        return "pages/order";
    }

    @RequestMapping("/delete/good")
    public String deleteGood(@RequestParam("goodId") Long goodId) {
        Cart cart = Utils.getCart(request);
        Good good = goodService.getGoodById(goodId);
        if (good != null)
            // cart.getGoods().remove(good);
        cartService.flush();
        request.getSession().setAttribute(Constants.CART, cart);
        //if (cart.getGoods().isEmpty())
        //return "redirect:/cart";

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
        Cart cart = Utils.getCart(request);
        // cart.setGoods(new HashSet<Good>());
        cartService.update(cart);

        Address address1 = new Address(city, street, house, flat, index);
        addressService.add(address1);

        Order order = new Order(user, address1, new Date(), 0.0, "to check", "webMoney");
        orderService.add(order);

        if (user != null) {
            user.getAddress().add(address1);
            user.getOrders().add(order);
            userService.update(user);
        }
        return "redirect:/catalog";
    }
}
