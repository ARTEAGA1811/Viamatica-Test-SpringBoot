package com.viamatica.viamatica.business.service;

import com.viamatica.viamatica.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.viamatica.viamatica.domain.dto.User userDomain = userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));

        Collection<? extends GrantedAuthority> authorities = userDomain.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
        return new User(
                userDomain.getUsername(),
                userDomain.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
