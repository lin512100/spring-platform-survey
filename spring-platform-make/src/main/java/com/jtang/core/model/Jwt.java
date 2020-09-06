package com.jtang.core.model;

import com.jtang.core.utils.JwtUtils;

public class Jwt {

    /* 头部 */
    private String header;
    /* 负载 */
    private String payload;
    /* 签名 */
    private String signature;

    public Jwt(String payload) throws Exception {
        this.header = JwtUtils.encode(JwtUtils.DEFAULT_HEADER);
        this.payload = JwtUtils.encode(payload);
        this.signature = JwtUtils.getSignature(payload);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    /** jwt最终结果 */
    @Override
    public String toString() {
        return header + "." + payload + "." + signature;
    }
}
