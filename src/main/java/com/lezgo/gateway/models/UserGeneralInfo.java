package com.lezgo.gateway.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGeneralInfo {
    private String city;
    private double lat;
    private double lng;
    private String image;
}
