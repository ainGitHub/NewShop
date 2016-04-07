package com.shop.itis.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class NavbarAspect {
    @Autowired
    HttpServletRequest request;

    private MessageSourceAccessor msa;

    @Autowired
    private void setMessageSourceAccessor(MessageSource messageSource) {
        this.msa = new MessageSourceAccessor(messageSource);
    }

    @Pointcut("@annotation(com.shop.itis.annotation.CategoryMenu)")
    public void navbarPointcut() {
    }

    @Before("navbarPointcut()")
    public void checkNavbarPointcut() {
        request.getSession().setAttribute("menuHome", this.msa.getMessage("menu.home"));
        request.getSession().setAttribute("menuSupport", this.msa.getMessage("menu.support"));
        request.getSession().setAttribute("menuAccount", this.msa.getMessage("menu.account"));
        request.getSession().setAttribute("menuCart", this.msa.getMessage("menu.cart"));
        request.getSession().setAttribute("menuContacts", this.msa.getMessage("menu.contacts"));
        request.getSession().setAttribute("menuCartWords", this.msa.getMessage("menu.cartWords"));
        request.getSession().setAttribute("menuSum", this.msa.getMessage("menu.sum"));
        request.getSession().setAttribute("menuGoods", this.msa.getMessage("menu.goods"));
    }


}
