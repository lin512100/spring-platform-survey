package com.jtang.common.model.account.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 允许访问的操作
 * @author linjt
 * @date 2020/7/20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HandleAllow implements Serializable {

    /** 模块名称 */
    private String server;

    /** 访问路径 */
    private String url;

    /** 访问方法 */
    private String method;


    /** 重写equals方法 */
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            //地址相等
            return true;
        }

        //非空性：对于任意非空引用x，x.equals(null)应该返回false。
        if(obj == null){
            return false;
        }

        if(obj instanceof HandleAllow){
            HandleAllow other = (HandleAllow) obj;
            return this.server.equals(other.server) && this.url.equals(other.url);
        }

        return false;
    }

    /** 重写hashCode方法 */
    @Override
    public int hashCode() {
        String str = url + method;
        return str.hashCode();
    }



}
