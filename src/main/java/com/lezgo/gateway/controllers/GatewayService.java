package com.lezgo.gateway.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lezgo.gateway.models.events.Event;
import com.lezgo.gateway.models.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class GatewayService {

    @Autowired
    private EventGatewayService eventGatewayService;

    ResponseEntity authenticate(AuthenticationRequest authenticationRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(authenticationRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/authenticate"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type","application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if(response.body().contains("jwt")){
                return ResponseEntity.ok(response.body());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("non authorized");
    }

    public ResponseEntity<?> signup(SignUpRequest signUpRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(signUpRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/signup"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type","application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if(response.body().contains("jwt")){
                return ResponseEntity.ok(response.body());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("user exists");
    }

    public ResponseEntity<?> getUserInfo(UserInfoRequest userInfoRequest){
        List<Event> events = eventGatewayService.getEventsParticipatedInByOwnerUsername(userInfoRequest.getUsername());
        List<Event> myEvents = eventGatewayService.getEventsByOwnerUsername(userInfoRequest.getUsername());
        UserDetails userDetails = getUserDetails(userInfoRequest);
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                                                            .myEvents(myEvents)
                                                            .events(events)
                                                            .userDetails(userDetails)
                                                            .build();
        return ResponseEntity.ok().body(userInfoResponse);
    }

    public UserDetails getUserDetails(UserInfoRequest userInfoRequest){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/userInfo?username="+userInfoRequest.getUsername()))
                .GET()
                .headers("Content-Type","application/json")
                .headers("Authorization", "Bearer "+userInfoRequest.getToken())
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), UserDetails.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserGeneralInfo getUserGeneralInfo(UserInfoRequest userInfoRequest){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/usergeneralinfo?username="+userInfoRequest.getUsername()))
                .GET()
                .headers("Content-Type","application/json")
                .headers("Authorization", "Bearer "+userInfoRequest.getToken())
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), UserGeneralInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<?> getUserCircle(UserInfoRequest userInfoRequest){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/usercircle?username="+userInfoRequest.getUsername()))
                .GET()
                .headers("Content-Type","application/json")
                .headers("Authorization", "Bearer "+userInfoRequest.getToken())
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.ok(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<?> checkFriendship(String username, String friendname, String token){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/friend?username="+username+"&"+"friendname="+friendname))
                .GET()
                .headers("Content-Type","application/json")
                .headers("Authorization", "Bearer "+token)
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.ok(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<?> addFriendship(FriendshipRequestWrapper friendshipRequestWrapper){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(FriendshipRequest.builder()
                            .username(friendshipRequestWrapper.getUsername())
                            .friendname(friendshipRequestWrapper.getFriendname()).build());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/addfriend"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type","application/json")
                .headers("Authorization", "Bearer "+ friendshipRequestWrapper.getToken())
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.ok(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("non authorized");
    }

    public ResponseEntity<?> deleteFriendship(String username, String friendname, String token){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/deletefriend?username="
                        +username+"&friendname="+friendname))
                .DELETE()
                .headers("Content-Type","application/json")
                .headers("Authorization", "Bearer "+ token)
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.ok(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("non authorized");
    }
}
