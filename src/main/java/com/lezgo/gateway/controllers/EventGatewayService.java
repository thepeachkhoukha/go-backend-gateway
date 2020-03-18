package com.lezgo.gateway.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lezgo.gateway.models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventGatewayService {

    ResponseEntity saveEvent(EventSaveRequest eventSaveRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(eventSaveRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2020/saveevent"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type","application/json")
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

    public ResponseEntity<?> reportEvent(EventReportRequest eventReportRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(eventReportRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2020/reportevent"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type","application/json")
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

    public ResponseEntity<?> getEvents(EventsFetchRequest eventsFetchRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(eventsFetchRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2020/nearme"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type","application/json")
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

    public ResponseEntity joinEvent(EventJoinRequest eventJoinRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(eventJoinRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2020/joinevent"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type","application/json")
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

    public ResponseEntity<?> getTopEvent(EventTopRequest eventTopRequest){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper
                    .writeValueAsString(eventTopRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2020/topevents"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers("Content-Type","application/json")
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

    public List<Event> getEventsParticipatedInByOwnerUsername(String username){
        List<Event> events = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2020/participatedin?username="+username))
                .GET()
                .headers("Content-Type","application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray inputArray = new JSONArray(response.body());

            for (int i = 0; i < inputArray.length(); i++){
                JSONObject jo = inputArray.getJSONObject(i);
                ObjectMapper o = new ObjectMapper();
                Event event = o.readValue(jo.toString(), Event.class);
                events.add(event);
            }
            return events;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<Event> getEventsByOwnerUsername(String username){
        List<Event> events = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2020/byusername?ownerUsername="+username))
                .GET()
                .headers("Content-Type","application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray inputArray = new JSONArray(response.body());

            for (int i = 0; i < inputArray.length(); i++){
                JSONObject jo = inputArray.getJSONObject(i);
                ObjectMapper o = new ObjectMapper();
                Event event = o.readValue(jo.toString(), Event.class);
                events.add(event);
            }
            return events;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return events;
    }

    public int getNumberEventsToday(String username){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:2020/today?username="+username))
                .GET()
                .headers("Content-Type","application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Integer.parseInt(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
