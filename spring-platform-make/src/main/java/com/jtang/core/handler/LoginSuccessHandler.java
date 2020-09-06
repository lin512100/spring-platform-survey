package com.jtang.core.handler;

import com.alibaba.fastjson.JSONObject;
import com.jtang.base.utils.ResultUtils;
import com.jtang.core.model.Jwt;
import com.jtang.core.utils.JwtUtils;
import com.jtang.system.entity.SysUser;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功 handler
 * @author lin512100
 * @date 2020/9/6
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 获取登录成功信息
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        boolean loginBoolean = true;

        SysUser user = (SysUser) authentication.getPrincipal();
        user.setPassword(null);
        long now = System.currentTimeMillis();

        JSONObject payload = new JSONObject();
        //签发人
        payload.put("iss","sys");
        //受众
        payload.put("aud",user.getUsername());
        //过期时间
        payload.put("exp",now + JwtUtils.EXPIRE_TIME);
        //生效时间
        payload.put("nbf",now);
        //签发时间
        payload.put("iat",now);
        //编号
        payload.put("jti", user.getId());
        //主题
        payload.put("sub","JWT-TEST");
        //用户对象
        payload.put("user",user);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",user.getId());
            jsonObject.put("access_token",new Jwt(payload.toJSONString()).toString());
            jsonObject.put("username",user.getUsername());
            response.getWriter().write(ResultUtils.build(jsonObject).toString());
        } catch (Exception e) {
            response.getWriter().write(ResultUtils.fail.toString());

        }
    }
}

