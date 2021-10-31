package com.nebula.practicevueblog.controller;


import com.nebula.practicevueblog.common.lang.Result;
import com.nebula.practicevueblog.entity.Blog;
import com.nebula.practicevueblog.entity.User;
import com.nebula.practicevueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nebula
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 测试
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object testFindUser(@PathVariable("id") Long id){
        return userService.getById(id);
    }

    /**
     * 测试，要求需要登录才能访问 通过@RequiresAuthentication实现
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User user = userService.getById(1L);
        return Result.success(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.success(user);
    }
}
