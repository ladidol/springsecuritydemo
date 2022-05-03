package com.feng.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @projectName: springboot2
 * @package: com.feng.entity
 * @className: LoginUser
 * @author: Ladidol
 * @description: 实现UserDetails接口
 * @date: 2022/4/28 22:24
 * @version: 1.0
 */

@Data
@NoArgsConstructor
//这里需要把不是属性的get方法给忽略掉, 避免转json的时候不能行!
@JsonIgnoreProperties({"username","password","enabled","accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
//@JsonIgnoreProperties({"authorities"})
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permissions;

    @JSONField(serialize = false) //保存到Redis中会避免乱码问题
    private List<SimpleGrantedAuthority> authorities;

    //两个参数的构造方法
    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null){
            return authorities;
        }
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        authorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //先改成true
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
