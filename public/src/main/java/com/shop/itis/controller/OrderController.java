package com.shop.itis.controller;

import com.shop.itis.MailService;
import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.Address;
import com.shop.itis.domain.Good;
import com.shop.itis.domain.GoodWrapper;
import com.shop.itis.domain.User;
import com.shop.itis.service.AddressService;
import com.shop.itis.service.GoodService;
import com.shop.itis.service.OrderService;
import com.shop.itis.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    AddressService addressService;

    @Autowired
    OrderService orderService;

    @Autowired
    MailService mailService;

    @Autowired
    Configuration freemarkerConfiguration;

    @CategoryMenu
    @RequestMapping(method = RequestMethod.GET)
    public String page(ModelMap map) {
        User user = Utils.getAutentificationUser(userService);
        map.put("user", user);
        map.put(Constants.ATTR_ADDRESS_FORM, new Address());

        List<Address> userAddress = addressService.userAddress(user);
        map.put("userAddress", userAddress);
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

        Set<GoodWrapper> goods = Utils.getAttributeCartGoods(request);
        Double sum = 0.0;
        for (GoodWrapper userGoods : goods) {
            sum += userGoods.getGood().getPrice() * userGoods.getCount();
        }

        address.setUser(user);
        addressService.update(address);

        orderService.add(user, address, goods, "to check", "webMoney");
        String text = null;
        try {
            map.put(Constants.CART_GOODS, goods);
            text = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("order.ftl", "UTF-8"), map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        if (text != null)
            mailService.sendMail(user.getMail(), "Orders", text);


        Utils.addAttributes(new HashSet<GoodWrapper>(), 0.0, 0, request);

        return "redirect:/catalog";
    }
}
