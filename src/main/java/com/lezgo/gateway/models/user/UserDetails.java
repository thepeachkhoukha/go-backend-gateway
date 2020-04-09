package com.lezgo.gateway.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private int id;
    private String username;
    private String name;
    private String location;
    private String university;
    private String occupation;
    private int lat;
    private int lng;
    private String image;
}
