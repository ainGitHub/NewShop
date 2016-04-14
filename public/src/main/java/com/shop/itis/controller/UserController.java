package com.shop.itis.controller;

import com.shop.itis.MailService;
import com.shop.itis.Utils.Constants;
import com.shop.itis.Utils.Utils;
import com.shop.itis.annotation.CategoryMenu;
import com.shop.itis.domain.Cart;
import com.shop.itis.domain.Order;
import com.shop.itis.domain.UserInfo;
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
import java.util.List;

@Controller
public class UserController {

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    MailService mailService;


    /**
     * @param registrationFormBean - форма приходящая от юзера
     * @param bindingResult        - проверяет форму
     * @param photo                - файл(фото) от формы
     * @return
     */
    @CategoryMenu
    @RequestMapping(value = "/registr", method = RequestMethod.POST)
    public String registrate(
            ModelMap map,
            @Valid @ModelAttribute(Constants.ATTR_REGISTRATION_FORM) RegistrationFormBean registrationFormBean,
            BindingResult bindingResult,
            @RequestParam("photo") MultipartFile photo
    ) {

        if (bindingResult.hasErrors()) {
            return "auth/registr";
        }

        checkPhoto(photo);

        UserInfo userInfo = createUser(registrationFormBean, photo);
        servletRequest.getSession().setAttribute("registrUser", userInfo);

        UserInfo inBD = userService.getUserByUsername(userInfo.getUsername());

        if (inBD != null) {
            map.put("message", "Пользователь с именем " + userInfo.getUsername() + " уже существует!");
            return "auth/registr";
        }


        userService.add(userInfo);

        UserRoles roles = createRoleForUser(userInfo);
        roleService.add(roles);
        return "redirect:/mail/registr";
    }

    /**
     * @param userInfo Создает SpringSecurity роли для юзера
     * @return
     */
    private UserRoles createRoleForUser(UserInfo userInfo) {
        UserRoles roles = new UserRoles();
        roles.setRole(Constants.USER);
        roles.setUserInfo(userInfo);
        return roles;
    }

    /**
     * @param registrationFormBean - форма от юзера
     * @param photo                - фото от юзера
     *                             создает самого юзера, с фото или без
     * @return
     */
    private UserInfo createUser(RegistrationFormBean registrationFormBean, MultipartFile photo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(registrationFormBean.getUsername());
        userInfo.setPassword(Utils.md5Apache(registrationFormBean.getPassword()));
        userInfo.setMail(registrationFormBean.getEmail());
        userInfo.setEnabled(false);
        if (!photo.isEmpty()) userInfo.setAvatar(photo.getOriginalFilename());
        return userInfo;
    }

    /**
     * @param photo - фото от юзера
     * Сохраняет фото от юзера
     * в папке target or (war)  /resources/image/userInfo
     */
    private void checkPhoto(MultipartFile photo) {
        String appPath = servletRequest.getServletContext().getRealPath("");
        String uploadsDirPath = appPath + "resources" + File.separator + "image" + File.separator + "userInfo";
        if (!photo.isEmpty()) {
            try {
                File dir = new File(uploadsDirPath + File.separator + photo.getOriginalFilename());

                if (!dir.exists()) {
                    dir.createNewFile();
                }

                photo.transferTo(dir);

            } catch (Exception e) {
                System.out.println("exception");
            }
        } else {
            System.out.println("file empty");
        }
    }

    @CategoryMenu
    @RequestMapping(value = "/registr", method = RequestMethod.GET)
    public String registrPage() {
        servletRequest.setAttribute(Constants.ATTR_REGISTRATION_FORM, new RegistrationFormBean());
        return "auth/registr";
    }

    @CategoryMenu
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        UserInfo userInfo = (UserInfo) servletRequest.getSession().getAttribute("registrUser");
        if (userInfo != null && !userInfo.isEnabled()) {
            map.put("check", "Подтвердите регистрацию. Мы отправили вам ссылку на почту");
        }
        return "auth/login";
    }

    @CategoryMenu
    @RequestMapping(value = "/account")
    public String account(ModelMap map) {
        UserInfo userInfo = Utils.getAutentificationUser(userService);
        userInfo = userService.getUserByUsername(userInfo.getUsername());
        Cart cart = (Cart) servletRequest.getSession().getAttribute(Constants.CART);
        if (cart != null)
            userInfo.setCart(cart);
        userService.update(userInfo);

        servletRequest.getSession().setAttribute(Constants.USER, userInfo);

        List<Order> orders = userInfo.getOrders();
        map.put("orders", orders);
        map.put("userInfo", userInfo);
        return "auth/account";
    }
}
