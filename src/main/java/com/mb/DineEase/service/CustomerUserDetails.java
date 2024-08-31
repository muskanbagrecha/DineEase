package com.mb.DineEase.service;

import com.mb.DineEase.model.user.User;
import com.mb.DineEase.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        String role = user.get().getRole();
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorityList);
    }

}
