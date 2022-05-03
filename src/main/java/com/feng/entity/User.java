package com.feng.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: springboot2
 * @package: com.feng.entity
 * @className: User
 * @author: Ladidol
 * @description:
 * @date: 2022/4/28 22:06
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
}
