package com.APIBackend.demo.Utils

import com.APIBackend.demo.Model.Users
import org.springframework.security.core.GrantedAuthority

class UserDetails(val user: Users): org.springframework.security.core.userdetails.UserDetails {
    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun getPassword()=user.senha

    override fun getUsername()=user.login

    override fun isAccountNonExpired()=true

    override fun isAccountNonLocked()=true

    override fun isCredentialsNonExpired()=true

    override fun isEnabled()=true
}