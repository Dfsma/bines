package com.dfsma.bin.authservice.security.services;

import com.dfsma.bin.authservice.entity.UserEntity;
import com.dfsma.bin.authservice.repository.UserRepository;
import com.dfsma.bin.authservice.security.jwt.JwtUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String dscEmail) throws UsernameNotFoundException {
        UserEntity user = userRepository.findDscEmail(dscEmail).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado, " + dscEmail));
        if (null == user) {
            logger.info("Username {} not found", dscEmail);
        }else{
            logger.info("Username {} found", user.getDscEmail());
        }
        return UserDetailsImpl.build(user);
    }
}
