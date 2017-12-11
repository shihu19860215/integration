package com.shihu.controller;

import com.shihu.base.Base;
import com.shihu.util.MD5Util;
import com.shihu.model.common.User;
import com.shihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(HttpSession session,User user){
        ModelAndView modelAndView=new ModelAndView();
        Object o=session.getAttribute("user");
        if(null!=o){
            modelAndView.setViewName("redirect:/home");
        }else {
            if(null==user.getUsername()||null==user.getPassword()){
                modelAndView.setViewName("sign-in");
            }else {
                user.setPassword(MD5Util.MD5(user.getPassword()));
                User resultUser=userService.getUserByUP(user);
                if(null==resultUser){//username password错误
                    modelAndView.setViewName("sign-in");
                    modelAndView.addObject("errorinfo", Base.USER_PASSWORD_ERROR.getInfo());
                }else {
                    session.setAttribute("user",resultUser);
                    modelAndView.setViewName("redirect:/home");
                }
            }

        }
        return modelAndView;
    }

    @RequestMapping("/loginout")
    public String login(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/home";
    }
}
