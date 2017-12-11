package com.shihu.interceptor;

import com.shihu.model.common.User;
import com.shihu.mybatis.dao.AuthorityDao;
import com.shihu.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Component
public class AuthorityValidateInterceptor implements HandlerInterceptor {
    private Set<String> authoritySet;
    @Autowired
    private AuthorityDao authorityDao;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result=true;
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String str=handlerMethod.getBeanType().getSimpleName()+":"+handlerMethod.getMethod().getName();
            if (getAuthoritySet().contains(str)){
                HttpSession session=request.getSession();
                Object o=session.getAttribute("user");
                if(null==o){
                    result=false;
                }else {
                    User user=(User)o;
                    if(!user.getAuthorityStrSet().contains(str)){
                        result=false;
                    }
                }
            }
        }
        if(!result){
            response.sendRedirect("/login");
        }
        return result;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    private Set<String> getAuthoritySet(){
        if(null==this.authoritySet){
            this.authoritySet=authorityDao.getAuthorityStrAll();
        }
        return this.authoritySet;
    }
}
