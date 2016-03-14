package com.shop.itis.controller;

import com.shop.itis.Utils.Utils;
import com.shop.itis.domain.User;
import com.shop.itis.domain.UserRoles;
import com.shop.itis.service.RoleService;
import com.shop.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class UserController {

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/registr", method = RequestMethod.POST)
    public String registrate(@RequestParam("photo") MultipartFile photo,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("email") String email) {
        String appPath = servletRequest.getServletContext().getRealPath("");
        String uploadsDirPath = appPath + "resources" + File.separator + "image" + File.separator + "user";
        System.out.println(uploadsDirPath);
        if (!photo.isEmpty()) {
            try {
                File dir = new File(uploadsDirPath + File.separator + photo.getOriginalFilename());

                if (!dir.exists()) {
                    dir.createNewFile();
                }

                System.out.println("herr");
                photo.transferTo(dir);
                System.out.println("here");

            } catch (Exception e) {
                System.out.println("exception");
            }
        } else {
            System.out.println("file empty");
        }


        User user = new User();
        user.setUsername(username);
        user.setPassword(Utils.md5Apache(password));
        user.setMail(email);
        user.setEnabled(true);
        if (!photo.isEmpty()) user.setAvatar(photo.getOriginalFilename());
        userService.add(user);

        UserRoles roles = new UserRoles();
        roles.setRole(Utils.USER);
        roles.setUser(user);
        roleService.add(roles);
        return "pages/login";
    }

    @RequestMapping(value = "/registr", method = RequestMethod.GET)
    public String registrPage() {
        return "pages/registr";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "pages/login";
    }

    @RequestMapping(value = "/account")
    public String account(ModelMap map) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User real = userService.getUserByUsername(user.getUsername());

        map.put("user", real);
        return "pages/account";
    }
}
