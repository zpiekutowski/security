package com.zbiir.spring_security_mysql.service;

import com.zbiir.spring_security_mysql.model.MyUser;
import com.zbiir.spring_security_mysql.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NewUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUser addNewUser(MyUser newUser) throws Exception {
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new Exception("User Alredy exist");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return newUser;
    }


}
