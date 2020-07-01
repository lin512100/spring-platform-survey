package com.jtang.oauth.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtang.common.model.auth.OauthClientDetails;
import com.jtang.oauth.service.IClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * 自定义客户实现类
 * @date 2020/7/1 23:26
 * @author LinJinTang
 */
@Slf4j
public class CustomerClientDetailServiceImpl implements ClientDetailsService{

    @Autowired
    private IClientDetailsService iClientDetailsService;

    @Override
    public org.springframework.security.oauth2.provider.ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientId);
        OauthClientDetails oauthClientDetails = iClientDetailsService.getOne(queryWrapper);
        if(oauthClientDetails == null){
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }
        BaseClientDetails baseClientDetails= new BaseClientDetails(oauthClientDetails.getClientId(), oauthClientDetails.getResourceIds(),
                oauthClientDetails.getScope(), oauthClientDetails.getAuthorizedGrantTypes(),
                oauthClientDetails.getAuthorities(), oauthClientDetails.getWebServerRedirectUri());
        baseClientDetails.setClientSecret(oauthClientDetails.getClientSecret());
        return baseClientDetails;
    }
}
