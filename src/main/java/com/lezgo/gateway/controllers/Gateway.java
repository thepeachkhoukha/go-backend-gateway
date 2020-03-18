package com.lezgo.gateway.controllers;

import com.lezgo.gateway.models.AuthenticationRequest;
import com.lezgo.gateway.models.SignUpRequest;
import com.lezgo.gateway.models.UserInfoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface Gateway {
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest);

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest);

    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    ResponseEntity<?> getUserInfo(@RequestBody UserInfoRequest userInfoRequest);

    @RequestMapping(value = "/getusergeneralinfo", method = RequestMethod.POST)
    ResponseEntity<?> getUserGeneralInfo(@RequestBody UserInfoRequest userInfoRequest);
}
