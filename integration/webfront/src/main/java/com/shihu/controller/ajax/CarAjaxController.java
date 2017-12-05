package com.shihu.controller.ajax;

import com.google.gson.Gson;
import com.shihu.model.common.VO.CarVO;
import com.shihu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ajax/car")
public class CarAjaxController {
    public static Gson gson=new Gson();
    @Autowired
    private CarService carService;

    @RequestMapping("/saerchcarbyname")
    public void addNum(HttpServletResponse response, String str){
        List<CarVO> cars=carService.listCarVOWithCarTypeNameIncludeStr(str);
        try {
            //设置页面不缓存
            response.setContentType("application/json");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out= null;
            out = response.getWriter();
            out.print(gson.toJson(cars));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
