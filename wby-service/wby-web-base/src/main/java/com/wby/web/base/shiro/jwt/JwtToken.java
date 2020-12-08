package com.wby.web.base.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description token
 * @Author JacksonTu
 * @Date 2020/4/5 13:31
 */
public class JwtToken implements AuthenticationToken {
    // 密钥
    private String token;

    public JwtToken() {
    }

    public JwtToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
