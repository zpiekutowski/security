package com.zbiir.spring_security_mysql.controler;


import com.zbiir.spring_security_mysql.model.MyUser;
import com.zbiir.spring_security_mysql.service.JwtService;
import com.zbiir.spring_security_mysql.service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {

    @Autowired
    private NewUserService newUserService;
    @Autowired
    private JwtService jwtService;


    @GetMapping("/home")
    public String home() {
        return "Welcome anyone";
    }

    @GetMapping("/user")
    public String user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return "Welcome USER: ";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome ADMIN";
    }

    @PostMapping("/add_user")
    public MyUser addUser(@RequestBody MyUser newUser) throws Exception {
        return newUserService.addNewUser(newUser);
        //return "User corectly created";
    }

    @GetMapping("/jwt_token")
    public String getJWTToken() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return jwtService.getToken(username);

    }

}
