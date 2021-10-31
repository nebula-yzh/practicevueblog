package com.nebula.practicevueblog.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nebula.practicevueblog.common.dto.LoginDTO;
import com.nebula.practicevueblog.common.lang.Result;
import com.nebula.practicevueblog.entity.User;
import com.nebula.practicevueblog.service.UserService;
import com.nebula.practicevueblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nebula
 * @date 2021/10/30 17:37
 * @description: 用户登录，退出登录
 */
@RestController
public class AccountController {
    @Resource
    UserService userService;

    @Resource
    JwtUtils jwtUtils;

    /**
     * 默认账号密码：markerhub / 111111
     *
     */
    //@CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDto, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");

        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
