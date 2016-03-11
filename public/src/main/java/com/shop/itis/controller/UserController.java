package com.shop.itis.controller;

import com.shop.itis.Utils.Utils;
import com.shop.itis.domain.User;
import com.shop.itis.domain.UserRoles;
import com.shop.itis.service.RoleService;
import com.shop.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/registr", method = RequestMethod.POST)
    public String registrate(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(Utils.md5Apache(password));
        user.setMail(email);
        user.setEnabled(true);
        userService.add(user);

        UserRoles roles = new UserRoles();
        roles.setRole(Utils.USER);
        roles.setUser(user);
        roleService.add(roles);

        return "login";
    }

    @RequestMapping(value = "/registr", method = RequestMethod.GET)
    public String registrPage() {
        return "registr";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String myAccount() {
        return "pages/account";
    }
}
