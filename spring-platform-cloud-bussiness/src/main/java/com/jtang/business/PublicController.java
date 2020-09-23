package com.jtang.business;

import com.jtang.resource.entity.TbResourceAddress;
import com.jtang.resource.service.ITbResourceAddressService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资源跳转
 * @author lin512100
 * @date 2020/9/3
 */
@Controller
@RequestMapping("/public/business")
public class PublicController {

    @Autowired
    private ITbResourceAddressService iTbResourceAddressService;

    /** 路由转发功能 */
    @GetMapping("/transfer/{uuid}/{channel}/{belong}/{source}")
    public void transfer(@PathVariable String uuid, @PathVariable Integer channel, @PathVariable Integer belong, @PathVariable String source, HttpServletResponse response) throws Exception {
        List<TbResourceAddress> resourceAddress = iTbResourceAddressService.getResourceAddress(uuid, channel, belong);
        if(resourceAddress.size() == 0){
            response.sendRedirect("http://www.baidu.com");
        }
        response.sendRedirect(resourceAddress.get(0).getUrl());
    }

}
