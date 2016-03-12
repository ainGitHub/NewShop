package com.shop.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mysite");
        return "pages/catalog";
    }


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "pages/error";
    }

/*
    @RequestMapping(value = "/registr", method = RequestMethod.POST)
    public String adding(@RequestParam("img") MultipartFile file,
                         @RequestParam("login") String login,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email) {
        String appPath = request.getServletContext().getRealPath("");
        String uploadsDirPath = appPath + java.io.File.separator + "uploads";
        if (!file.isEmpty()) {
            try {
                java.io.File dir = new java.io.File(uploadsDirPath + java.io.File.separator + file.getOriginalFilename());

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                file.transferTo(dir);

                System.out.println("file writing" + dir.toString());


                File fileD = new File();
                fileD.setName(file.getOriginalFilename());
                fileD.setVeight(file.getSize());
                fileD.setFileAddres(uploadsDirPath + java.io.File.separator + file.getOriginalFilename());


                System.out.println("here1");
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setEmail(email);
                user.setPhotoAddres("uploads" + java.io.File.separator + file.getOriginalFilename());
                userService.add(user);

                System.out.println("here2");
                fileD.setUser(user);
                fileService.add(fileD);
            } catch (Exception e) {
                System.out.println("exception");
            }
        } else {
            System.out.println("file empty");
        }


        return "index";
    }*/

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String adminPage() {
        return "pages/search";
    }

}
