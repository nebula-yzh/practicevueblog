package com.nebula.practicevueblog.service.impl;

import com.nebula.practicevueblog.entity.User;
import com.nebula.practicevueblog.mapper.UserMapper;
import com.nebula.practicevueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nebula
 * @since 2021-10-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
