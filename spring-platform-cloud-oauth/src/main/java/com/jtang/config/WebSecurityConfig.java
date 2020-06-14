package com.jtang.config;

import com.jtang.filter.VerificationCodeFilter;
import com.jtang.oauth.entity.TbPersistentLogin;
import com.jtang.oauth.service.ITbPersistentLoginService;
import com.jtang.oauth.service.impl.TokenRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;


/**
 * 表单登录
 * @date 2020/6/13 0:19
 * @author LinJinTang
 */
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ITbPersistentLoginService iTbPersistentLoginService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        TokenRepositoryImpl tokenRepository = new TokenRepositoryImpl(iTbPersistentLoginService);

        http.authorizeRequests()
                .antMatchers("/admin/api/**").hasAnyAuthority("ADMIN")
                .antMatchers("/user/api/**").hasAnyAuthority("USER")
                .antMatchers("/app/api/**", "/captcha.jpg").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
//                .failureHandler(new SecurityAuthenticationFailureHandler())
//                .successHandler(new SecurityAuthenticationSuccessHandler())
//                .loginPage("/myLogin.html")
//                .loginProcessingUrl("/login")
                .permitAll()
//            .and()
//                .rememberMe()
//                .userDetailsService(userDetailsService)
//                // 2. 持久化令牌方案
//                .tokenRepository(tokenRepository)
//                // 7天有效期
//                .tokenValiditySeconds(60 * 60 * 24 * 7)
//            .and()
//                .logout()
//                .logoutUrl("/myLogout")
//                // 注销成功，重定向到该路径下
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
            .and()
            .sessionManagement()
                // 会话过期策略
                .invalidSessionUrl("/login")
                .maximumSessions(1).maxSessionsPreventsLogin(true);
//            .and()
//                .csrf()
//                .disable();
        // 将过滤器添加在UsernamePasswordAuthenticationFilter之前
//        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
