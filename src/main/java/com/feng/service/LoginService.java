package com.feng.service;

import com.feng.entity.User;
import com.feng.utils.result.Result;

/**
 * @projectName: springboot2
 * @package: com.feng.service
 * @className: LoginService
 * @author: Ladidol
 * @description:
 * @date: 2022/4/29 13:26
 * @version: 1.0
 */
public interface LoginService {
    Result login(User user);
}
