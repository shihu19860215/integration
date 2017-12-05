package com.shihu.controller.ajax;

import com.google.gson.Gson;
import com.shihu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ajax/product")
public class ProductAjaxController {
    public static Gson gson=new Gson();
    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public void addNum(HttpServletResponse response,Long productId){
        int count=productService.addProductNum(productId);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("count", count);
        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(gson.toJson(map));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/sub")
    public void subNum(HttpServletResponse response,Long productId){
        int count=productService.subProductNum(productId);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("count", count);
        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(gson.toJson(map));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
