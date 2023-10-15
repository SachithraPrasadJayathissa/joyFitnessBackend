package com.novozy.spring_new_jwt.controller;

import com.google.gson.Gson;
import com.novozy.spring_new_jwt.payload.entity.AuthRequest;
import com.novozy.spring_new_jwt.payload.entity.UserInfo;
import com.novozy.spring_new_jwt.repository.UserInfoRepository;
import com.novozy.spring_new_jwt.service.JwtService;
import com.novozy.spring_new_jwt.service.UserInfoService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserInfoRepository userInfoRepository;

    Gson gson = new Gson();




    @PostMapping("/generateToken")
    public ResponseEntity authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(authRequest.getUsername());
        if (userInfo.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                JSONObject object = new JSONObject();
                object.put("token", token);
                object.put("role", userInfo.get().getRoles());
                return new ResponseEntity<>(object, HttpStatus.OK);

            } else {
                throw new UsernameNotFoundException("Invalid Credentials");
            }
        } else {
            System.out.println("2");
            return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
        }

    }
}
