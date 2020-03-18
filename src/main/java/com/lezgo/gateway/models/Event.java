package com.lezgo.gateway.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private int id;
    private String ownerUsername;
    private String title;
    private String description;
    private String shortDescription;
    private String coverImage;
    private String location;
    private String city;
    private double lng;
    private double lat;
    private Timestamp date;
    private String startTime;
    private String endTime;
    private int threshold;
    private int maximum;
}
