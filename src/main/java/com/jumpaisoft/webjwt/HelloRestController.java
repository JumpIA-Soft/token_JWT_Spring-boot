//important add the package name for the classes
package com.jumpaisoft.webjwt;

import java.util.List;
import java.util.Optional;

import com.jumpaisoft.webjwt.Repository.UserRepository;
import com.jumpaisoft.webjwt.Repository.sessionRepository;
import com.jumpaisoft.webjwt.entities.session;
import com.jumpaisoft.webjwt.entities.user;
import com.jumpaisoft.webjwt.services.sessionServiceU;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//add the libraries for use in the classes
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin(origins = "*")
@RequestMapping("/api")
public class HelloRestController {
    // use final for assign just for once

    @Autowired
    sessionRepository sessionR;

    @Autowired
    sessionServiceU sessionSU;

    @Autowired
    UserRepository userR;

    @GetMapping("/user")
    public ResponseEntity<List<user>> helloUser() {
        List<user> list = userR.findAll();
        return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/findByName")
    public ResponseEntity<user> findByName(@RequestParam(value = "name") String name) {
        Optional<user> user = userR.findByNameUser(name);
        user u = user.get();
        return new ResponseEntity<user>(u, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/login")
    public session login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        if (sessionSU.verificationUser(username, pwd) == false) {
            return null;
        }

        JwtTokenUtil getToken = new JwtTokenUtil();

        String token = getToken.getJWTToken(username);
        // add new user login whit the respective token
        session userVerify = new session();
        userVerify.setUserName(username);
        userVerify.setTokenSession(token);
        sessionR.save(userVerify);

        return userVerify;
    }
}