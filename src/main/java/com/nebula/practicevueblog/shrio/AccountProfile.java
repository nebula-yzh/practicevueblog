package com.nebula.practicevueblog.shrio;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Nebula
 * @date 2021/10/30 16:03
 * @description: 对能够返回的用户信息，进行单独的封装
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;

    private String username;

    private String avatar;

    private String email;

}
