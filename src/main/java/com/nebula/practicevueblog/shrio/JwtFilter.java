package com.nebula.practicevueblog.shrio;

import cn.hutool.json.JSONUtil;
import com.nebula.practicevueblog.common.lang.Result;
import com.nebula.practicevueblog.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nebula
 * @date 2021/10/30 12:41
 * @description: jwt过滤器 访问其他接口或资源时，判断有无token，根据有无token进行处理(在用户已经登录的情况下)
 */
@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 拿到请求头里的token，并创建
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //强转成HttpServlet，获取请求头中的jwt
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(jwt)) {
            //new一个拿到的token的对象
            return new JwtToken(jwt);
        } else {
            return null;
        }
    }

    /**
     * 根据token，判断是否通过校验
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        //强转成HttpServlet，获取请求头中的jwt
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(jwt)) {
            //校验jwt
            Claims claim = jwtUtils.getClaimByToken(jwt);
            //若为空或token过期，抛出异常
            if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())) {
                throw new ExpiredCredentialsException("token已失效，请重新登录！");
            }
            //执行登录
            return executeLogin(request, response);

        } else {
            //没有token，不需要过滤器拦截，返回true
            return true;
        }
    }

    /**
     * 登录失败处理，输出异常信息到前端
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Throwable throwable = e.getCause() == null ? e : e.getCause();

        Result result = Result.fail(throwable.getMessage());

        String json = JSONUtil.toJsonStr(result);

        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException ioException) {

        }
        return false;
    }
}
