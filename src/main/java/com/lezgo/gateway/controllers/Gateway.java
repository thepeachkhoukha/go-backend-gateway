package com.lezgo.gateway.controllers;

import com.lezgo.gateway.models.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface Gateway {
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest);

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest);

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    ResponseEntity<?> getUserInfo(@RequestBody UserInfoRequest userInfoRequest);

    @RequestMapping(value = "/usergeneralinfo", method = RequestMethod.POST)
    ResponseEntity<?> getUserGeneralInfo(@RequestBody UserInfoRequest userInfoRequest);

    @RequestMapping(value = "/usercircle", method = RequestMethod.POST)
    ResponseEntity<?> getUserCircle(@RequestBody UserInfoRequest userInfoRequest);

    @RequestMapping(value = "/friend", method = RequestMethod.GET)
    ResponseEntity<?> checkFriendship(@RequestParam String username, @RequestParam String friendname, @RequestParam String token);

    @RequestMapping(value = "/addfriend", method = RequestMethod.POST)
    ResponseEntity<?> addFriendship(@RequestBody FriendshipRequestWrapper friendshipRequestWrapper);

    @RequestMapping(value = "/deletefriend", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteFriendship(@RequestParam String username, @RequestParam String friendname, @RequestParam String token);
}
