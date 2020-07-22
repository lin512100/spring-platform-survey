package com.jtang.common.service;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反向加载URL信息工具类
 * @author linjt
 * @date 2020/7/22
 */
@Slf4j
public class InitUrlService implements InitializingBean, ServletContextAware{

    /** 请求路径 */
    public final static String REFLEX_URL = "url";
    /** 类名 */
    public final static String REFLEX_CLASS_NAME = "className";
    /** 接口说明 */
    public final static String REFLEX_API_OPERATION_VALUE = "apiOperationValue";
    /** 接口发布说明 */
    public final static String REFLEX_API_OPERATION_NOTES = "apiOperationnotes";
    /** 请求方法类型 */
    public final static String REFLEX_TYPE = "type";

    public static List<HashMap<String, String>> urlList = new ArrayList<HashMap<String, String>>();

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    /** 获取url与类和方法的对应信息 */
    @Override
    public void setServletContext(ServletContext servletContext) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethodsMap = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethodsMap.entrySet()) {
            HashMap<String, String> model = new HashMap<String, String>();
            RequestMappingInfo info = item.getKey();
            HandlerMethod method = item.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                model.put(REFLEX_URL, url);
            }

            model.put(REFLEX_CLASS_NAME, method.getMethod().getDeclaringClass().getName());

            ApiOperation apiOperation = method.getMethodAnnotation(ApiOperation.class);
            if(apiOperation != null) {
                model.put(REFLEX_API_OPERATION_VALUE, apiOperation.value());
                model.put(REFLEX_API_OPERATION_NOTES, apiOperation.notes());
            }

            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            String type = methodsCondition.toString();
            if (type.startsWith("[") && type.endsWith("]")) {
                type = type.substring(1, type.length() - 1);
                model.put(REFLEX_TYPE, type);
            }
            urlList.add(model);
        }
        log.info("InitUrlService 反向加载URL完成");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 通过访问IP获取访问方法信息
     * @param lookupPath 查询的地址信息
     * @return 项目信息
     */
    public HashMap<String, String> findUrlByUrlList(String lookupPath){
        for(HashMap<String, String> item : urlList) {
            String url = item.getOrDefault("url", "");
            if(!StringUtils.isBlank(url) && url.equals(lookupPath)) {
                return item;
            }
        }
        return null;
    }

}
