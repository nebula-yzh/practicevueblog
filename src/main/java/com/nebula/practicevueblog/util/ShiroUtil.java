package com.nebula.practicevueblog.util;

import com.nebula.practicevueblog.shrio.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author Nebula
 * @date 2021/10/30 17:56
 * @description: 获取账号信息
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
