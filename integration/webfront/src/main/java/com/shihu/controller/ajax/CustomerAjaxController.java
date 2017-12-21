package com.shihu.controller.ajax;

import com.google.gson.Gson;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ajax/customer")
public class CustomerAjaxController {
    @Autowired
    private Gson gson;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/saerchlikename")
    public void searchLikeName(HttpServletResponse response, String customerNameOrTel){
        List<CustomerVO> list=customerService.getCustomerVOListLikeNameOrTel(customerNameOrTel);
        if(null==list)list=new ArrayList<CustomerVO>(0);
        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(gson.toJson(list));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
