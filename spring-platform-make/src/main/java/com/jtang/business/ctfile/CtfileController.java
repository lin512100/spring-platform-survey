package com.jtang.business.ctfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 城通网盘
 * @author lin512100
 * @date 2020/9/3
 */
@Controller
@RequestMapping("/business/ctfile")
public class CtfileController {

    @RequestMapping("/transfer")
    public String transfer(HttpServletRequest request) throws Exception {
        request.setAttribute("name", "name");
        System.out.println("================");
        return "redirect";
    }

}
