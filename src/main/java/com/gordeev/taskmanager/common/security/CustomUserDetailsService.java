package com.gordeev.taskmanager.common.security;

import com.gordeev.taskmanager.users.entity.User;
import com.gordeev.taskmanager.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
            new UsernameNotFoundException("Пользователя с именем '" + username + "' не существует")
        );

        Collection<? extends GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(user.getRole()));

        return new CustomUserDetails(user, authorities);
    }
}
