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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<com.viamatica.viamatica.domain.dto.User> optionalUserDomain = userRepository.getUserByUsername(usernameOrEmail);
        if (optionalUserDomain.isEmpty()) {
            optionalUserDomain = userRepository.getUserByEmail(usernameOrEmail);
        }
        if (optionalUserDomain.isEmpty()) {
            throw new UsernameNotFoundException("User not found by username or email: " + usernameOrEmail);
        }


        Collection<? extends GrantedAuthority> authorities = optionalUserDomain.get().getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
        return new User(
                optionalUserDomain.get().getUsername(),
                optionalUserDomain.get().getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
