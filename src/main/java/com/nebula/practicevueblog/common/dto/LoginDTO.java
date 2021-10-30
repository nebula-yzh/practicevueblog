package com.nebula.practicevueblog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Nebula
 * @date 2021/10/30 17:38
 * @description: 登录页面的Data Transfer Object
 */
@Data
public class LoginDTO implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
