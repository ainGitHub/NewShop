package com.shop.itis.controller;

import com.shop.itis.Utils.Utils;
import com.shop.itis.domain.User;
import com.shop.itis.domain.UserRoles;
import com.shop.itis.form.RegistrationFormBean;
import com.shop.itis.service.RoleService;
import com.shop.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;

@Controller
public class UserController {

    private final String ATTR_REGISTRATION_FORM = "regform";

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/registr", method = RequestMethod.POST)
    public String registrate(
            @Valid @ModelAttribute(ATTR_REGISTRATION_FORM) RegistrationFormBean registrationFormBean,
            BindingResult bindingResult,
            @RequestParam("photo") MultipartFile photo) {

        if (bindingResult.hasErrors()) {
            return "pages/registr";
        }

        checkPhoto(photo);

        User user = new User();
        user.setUsername(registrationFormBean.getUsername());
        user.setPassword(Utils.md5Apache(registrationFormBean.getPassword()));
        user.setMail(registrationFormBean.getEmail());
        user.setEnabled(true);
        if (!photo.isEmpty()) user.setAvatar(photo.getOriginalFilename());
        userService.add(user);

        UserRoles roles = new UserRoles();
        roles.setRole(Utils.USER);
        roles.setUser(user);
        roleService.add(roles);
        return "pages/login";
    }

    private void checkPhoto(MultipartFile photo) {
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
    }

    @RequestMapping(value = "/registr", method = RequestMethod.GET)
    public String registrPage() {
        servletRequest.setAttribute(ATTR_REGISTRATION_FORM, new RegistrationFormBean());
        return "pages/registr";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "pages/login";
    }

    @RequestMapping(value = "/account")
    public String account(ModelMap map) {
        User user = Utils.getAutentificationUser(userService);
        map.put("user", user);
        return "pages/account";
    }
}
