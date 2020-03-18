package com.lezgo.gateway.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String password;
    private String name;
    private String location;
    private String university;
    private String occupation;
    private int lat;
    private int lng;
    private String image;
}