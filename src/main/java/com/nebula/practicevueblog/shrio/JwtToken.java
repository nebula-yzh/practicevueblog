package com.nebula.practicevueblog.shrio;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Nebula
 * @date 2021/10/30 12:56
 * @description: JWT token
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
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
