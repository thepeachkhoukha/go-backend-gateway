package com.lezgo.gateway.models.user;

import lombok.*;

@NoArgsConstructor
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