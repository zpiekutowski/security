package com.zbiir.spring_security_mysql.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, String> {


    Optional<MyUser> findByUsername(String username);
}
