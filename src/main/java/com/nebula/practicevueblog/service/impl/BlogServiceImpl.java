package com.nebula.practicevueblog.service.impl;

import com.nebula.practicevueblog.entity.Blog;
import com.nebula.practicevueblog.mapper.BlogMapper;
import com.nebula.practicevueblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
