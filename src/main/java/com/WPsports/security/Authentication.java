package com.WPsports.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

public interface Authentication extends Principal, Serializable {
    // Authentication 저장소에 의해 인증된 사용자의 권한 목록
    Collection<? extends GrantedAuthority> getAuthorities();
    Object getCredentials();//비밀번호
    Object getDetails();//사용자 상세정보
    Object getPrincipal();//ID
    boolean isAuthenticated();//인증 여부
    void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;

}