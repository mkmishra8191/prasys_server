package com.prasys.auth.controller;

import com.prasys.auth.JwtUtil;
import com.prasys.auth.MyUserDetailsService;
import com.prasys.framework.entity.AuthRequest;
import com.prasys.framework.entity.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1")
public class AuthController {
    @Autowired
   private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @RequestMapping(value = "/login", method = RequestMethod.POST )

    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authRequest.getUserName());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final String jwt = jwtUtil.generateToken(userDetails);
        LoginResponse loginResponse = new LoginResponse(jwt,"Bearer",MyUserDetailsService.authorities.toString());

        return new ResponseEntity(loginResponse, HttpStatus.OK);

    }
    
}
