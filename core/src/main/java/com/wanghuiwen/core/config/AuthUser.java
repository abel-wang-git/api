package com.wanghuiwen.core.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AuthUser implements UserDetails  {
    private String userName;
    private String nickName;
    private String password;
    private Long id;
    private Byte type;
    private List<GrantedAuthority> authorities;
    private List<String> roles;
    private String avatar;
    private String level;
    private boolean enable;
    private boolean lock;
    private Date expiredTime;

    public AuthUser(String userName,
                    String nickName,
                    String password,
                    List<GrantedAuthority> authorities,
                    List<String> roles,
                    Long id,
                    Byte type,
                    String avatar,
                    boolean enable,
                    boolean lock,
                    Date expiredTime) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.authorities=authorities;
        this.roles=roles;
        this.type=type;
        this.id=id;
        this.avatar=avatar;
        this.enable=enable;
        this.lock=lock;
        this.expiredTime=expiredTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.expiredTime.compareTo(new Date()) > 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.lock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
