package com.jtang.business.ctfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 城通网盘
 * @author lin512100
 * @date 2020/9/3
 */
@Controller
@RequestMapping("/business/ctfile")
public class CtfileController {

    /** 路由转发功能 */
    @RequestMapping("/transfer")
    public void transfer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect("http://www.baidu.com");
    }

}
