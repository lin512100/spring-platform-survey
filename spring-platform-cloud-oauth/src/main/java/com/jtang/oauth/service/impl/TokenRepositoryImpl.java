package com.jtang.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.oauth.entity.TbPersistentLogin;
import com.jtang.oauth.service.ITbPersistentLoginService;
import core.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

/**
 * @date 2020/6/14 11:55
 * @author LinJinTang
 */
public class TokenRepositoryImpl implements PersistentTokenRepository {

    private ITbPersistentLoginService iTbPersistentLoginService;

    public TokenRepositoryImpl(ITbPersistentLoginService iTbPersistentLoginService){
        this.iTbPersistentLoginService = iTbPersistentLoginService;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        TbPersistentLogin tbPersistentLogin = new TbPersistentLogin();
        BeanUtils.copyProperties(persistentRememberMeToken, tbPersistentLogin);
        tbPersistentLogin.setToken(persistentRememberMeToken.getTokenValue());
        tbPersistentLogin.setLastUsed(DateUtils.asLocalDateTime(persistentRememberMeToken.getDate()));
//        // 仅允许一个用户记录密码登录
//        QueryWrapper<TbPersistentLogin> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(TbPersistentLogin::getUsername,persistentRememberMeToken.getUsername());
//        TbPersistentLogin loginServiceOne = iTbPersistentLoginService.getOne(queryWrapper);
//        if(loginServiceOne != null){
//            iTbPersistentLoginService.remove(queryWrapper);
//        }
        iTbPersistentLoginService.save(tbPersistentLogin);
    }

    @Override
    public void updateToken(String s, String s1, Date date) {
        QueryWrapper<TbPersistentLogin> queryWrapper  = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbPersistentLogin::getSeries,s);
        TbPersistentLogin tbPersistentLogin = iTbPersistentLoginService.getOne(queryWrapper);
        if(tbPersistentLogin != null){
            tbPersistentLogin.setLastUsed(DateUtils.asLocalDateTime(date));
            tbPersistentLogin.setToken(s1);
            iTbPersistentLoginService.update(tbPersistentLogin,queryWrapper);

        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        QueryWrapper<TbPersistentLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbPersistentLogin::getSeries, s);
        TbPersistentLogin a = iTbPersistentLoginService.getOne(queryWrapper);
        if(a != null){
            return new PersistentRememberMeToken(a.getUsername(),a.getSeries(),a.getToken(),DateUtils.asDate(a.getLastUsed()));
        }
        return null;
    }

    @Override
    public void removeUserTokens(String s) {
        QueryWrapper<TbPersistentLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TbPersistentLogin::getSeries, s);
        iTbPersistentLoginService.remove(queryWrapper);
    }
}
