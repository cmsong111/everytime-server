package com.untouchable.everytime.User.Service;

import com.untouchable.everytime.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
    }
}
