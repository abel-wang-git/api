package com.wanghuiwen.base.config.auth.service;

import com.wanghuiwen.base.model.Api;
import com.wanghuiwen.base.model.Role;
import com.wanghuiwen.base.model.User;
import com.wanghuiwen.base.service.ApiService;
import com.wanghuiwen.base.service.RoleService;
import com.wanghuiwen.base.service.UserService;
import com.wanghuiwen.core.config.AuthUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailsService implements UserDetailsService {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByLoginName(s);
        if(user==null){
            throw new UsernameNotFoundException(messageSource.getMessage("login.fail", null, LocaleContextHolder.getLocale()));
        }
        //用户角色
        List<String> roles =  roleService.getByUser(user.getId()).stream()
                .map(Role::getName).distinct()
                .collect(Collectors.toList());

        //api 权限控制
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Api> powers = userService.getApis(user.getId());
        if (powers.size() > 0) {
            for (Api p : powers) {
                authorities.add(new SimpleGrantedAuthority(p.getUrl()));
            }
        }

        return new AuthUser(user.getLoginName(),
                user.getNickName(),
                user.getPassword(),
                authorities,
                roles,
                user.getId(),
                (byte)1,
                user.getAvatar(),
                user.getEnable(),
                user.getLocked(),
                new Date(user.getExpiredTime()));

    }
}
