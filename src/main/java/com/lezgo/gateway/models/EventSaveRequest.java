package com.lezgo.gateway.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
public class EventSaveRequest {
    @NotBlank
    private String ownerUsername;

    @NotBlank
    private String title;

    @NotBlank
    private String location;

    @NotBlank
    private String city;

    @NotBlank
    private String description;

    @NotBlank
    private String shortDescription;

    @NotBlank
    private String coverImage;

    private Timestamp date;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;

    @NotNull
    private int threshold;

    @NotNull
    private int maximum;
}