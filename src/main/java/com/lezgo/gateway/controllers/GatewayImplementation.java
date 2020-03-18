package com.lezgo.gateway.controllers;

import com.lezgo.gateway.models.AuthenticationRequest;
import com.lezgo.gateway.models.SignUpRequest;
import com.lezgo.gateway.models.UserInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GatewayImplementation implements Gateway{
    @Autowired
    private GatewayService gatewayService;

    @Override
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return gatewayService.authenticate(authenticationRequest);
    }

    @Override
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        return gatewayService.signup(signUpRequest);
    }

    @Override
    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    public ResponseEntity<?> getUserInfo(@RequestBody UserInfoRequest userInfoRequest){
        return gatewayService.getUserInfo(userInfoRequest);
    }

    @RequestMapping(value = "/getusergeneralinfo", method = RequestMethod.POST)
    public ResponseEntity<?> getUserGeneralInfo(@RequestBody UserInfoRequest userInfoRequest){
        return ResponseEntity.ok().body(gatewayService.getUserGeneralInfo(userInfoRequest));
    }
}
