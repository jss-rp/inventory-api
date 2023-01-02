package com.jss.inventory.service.impl;

import com.jss.inventory.entity.User;
import com.jss.inventory.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(final String username) {
        return userRepository
                .findByUsername(username)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UsernameNotFoundException("User not found."))))
                .map(User::toAuthenticatedUser);
    }
}
