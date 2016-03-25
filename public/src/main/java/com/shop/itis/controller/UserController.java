package com.shop.itis.controller;

import com.shop.itis.MailService;
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

    @Autowired
    MailService mailService;


    /**
     * @param registrationFormBean - форма приходящая от юзера
     * @param bindingResult        - проверяет форму
     * @param photo                - файл(фото) от формы
     * @return
     */
    @RequestMapping(value = "/registr", method = RequestMethod.POST)
    public String registrate(
            @Valid @ModelAttribute(ATTR_REGISTRATION_FORM) RegistrationFormBean registrationFormBean,
            BindingResult bindingResult,
            @RequestParam("photo") MultipartFile photo) {

        if (bindingResult.hasErrors()) {
            return "pages/registr";
        }

        checkPhoto(photo);

        User user = createUser(registrationFormBean, photo);
        userService.add(user);

        mailService.sendMail(registrationFormBean.getEmail(), Utils.EMAIL_SUBJECT, Utils.getEmailText(registrationFormBean.getEmail()));

        UserRoles roles = createRoleForUser(user);
        roleService.add(roles);
        return "pages/login";
    }

    /**
     * @param user Создает SpringSecurity роли для юзера
     * @return
     */
    private UserRoles createRoleForUser(User user) {
        UserRoles roles = new UserRoles();
        roles.setRole(Utils.USER);
        roles.setUser(user);
        return roles;
    }

    /**
     * @param registrationFormBean - форма от юзера
     * @param photo                - фото от юзера
     *                             создает самого юзера, с фото или без
     * @return
     */
    private User createUser(RegistrationFormBean registrationFormBean, MultipartFile photo) {
        User user = new User();
        user.setUsername(registrationFormBean.getUsername());
        user.setPassword(Utils.md5Apache(registrationFormBean.getPassword()));
        user.setMail(registrationFormBean.getEmail());
        user.setEnabled(true);
        if (!photo.isEmpty()) user.setAvatar(photo.getOriginalFilename());
        return user;
    }

    /**
     * @param photo - фото от юзера
     * Сохраняет фото от юзера
     * в папке target or (war)  /resources/image/user
     */
    private void checkPhoto(MultipartFile photo) {
        String appPath = servletRequest.getServletContext().getRealPath("");
        String uploadsDirPath = appPath + "resources" + File.separator + "image" + File.separator + "user";
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
