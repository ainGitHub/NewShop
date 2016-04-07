package com.shop.itis.service;


import com.shop.itis.domain.UserInfo;
import com.shop.itis.domain.UserRoles;
import com.shop.itis.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.getUserByUsername(username);

        if (userInfo == null)
            return new User("guest", "", true, true, true, true, new ArrayList<GrantedAuthority>());

        List<GrantedAuthority> grantedAuthorities = buildUserAuthority(userInfo.getRoles());
        return buildUserForAuthentication(userInfo, grantedAuthorities);
    }

    // Converts com.shop.itis.domain.UserInfo userInfo to
    // org.springframework.security.core.userdetails.UserInfo
    private User buildUserForAuthentication(UserInfo userInfo, List<GrantedAuthority> authorities) {
        return new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRoles> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build userInfo's authorities
        for (UserRoles userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
