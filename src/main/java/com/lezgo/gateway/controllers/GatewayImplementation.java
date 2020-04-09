package com.lezgo.gateway.controllers;

import com.lezgo.gateway.models.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> getUserInfo(@RequestBody UserInfoRequest userInfoRequest){
        return gatewayService.getUserInfo(userInfoRequest);
    }

    @RequestMapping(value = "/usergeneralinfo", method = RequestMethod.POST)
    public ResponseEntity<?> getUserGeneralInfo(@RequestBody UserInfoRequest userInfoRequest){
        return ResponseEntity.ok().body(gatewayService.getUserGeneralInfo(userInfoRequest));
    }

    @RequestMapping(value = "/usercircle", method = RequestMethod.POST)
    public ResponseEntity<?> getUserCircle(@RequestBody UserInfoRequest userInfoRequest){
        return gatewayService.getUserCircle(userInfoRequest);
    }

    @RequestMapping(value = "/friend", method = RequestMethod.GET)
    public ResponseEntity<?> checkFriendship(@RequestParam String username, @RequestParam String friendname, @RequestParam String token){
        return gatewayService.checkFriendship(username, friendname, token);
    }

    @RequestMapping(value = "/addfriend", method = RequestMethod.POST)
    public ResponseEntity<?> addFriendship(@RequestBody FriendshipRequestWrapper friendshipRequestWrapper){
        return gatewayService.addFriendship(friendshipRequestWrapper);
    }

    @RequestMapping(value = "/deletefriend", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFriendship(@RequestParam String username, @RequestParam String friendname, @RequestParam String token){
        return gatewayService.deleteFriendship(username, friendname, token);
    }
}
