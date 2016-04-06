package com.shop.itis.controller;

import com.shop.itis.MailService;
import com.shop.itis.domain.User;
import com.shop.itis.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest servletRequest;

    @Autowired
    Configuration freemarkerConfiguration;

    @RequestMapping("/registr")
    public String sendMail() {
        User user = (User) servletRequest.getSession().getAttribute("registrUser");
        String text = null;
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("user", user);
            map.put("code", String.valueOf(user.getUsername().hashCode()));
            text = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("mail.ftl", "UTF-8"), map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        if (text != null)
            mailService.sendMail(user.getMail(), "Verify", text);
        return "redirect:/login";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String checkRegistr(@RequestParam("code") Integer code, RedirectAttributes redirectAttrs) {
        User user = (User) servletRequest.getSession().getAttribute("registrUser");

        if (user.getUsername().hashCode() == code) {
            redirectAttrs.addFlashAttribute("message", "Вы успешно зарегистрировались на нашем сайте можете зайти на свою страницу");
            user.setEnabled(true);
            userService.update(user);
        }
        return "redirect:/catalog";
    }
}
