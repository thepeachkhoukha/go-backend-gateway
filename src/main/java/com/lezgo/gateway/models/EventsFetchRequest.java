package com.lezgo.gateway.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EventsFetchRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String city;

    @NotNull
    private int lng;

    @NotNull
    private int lat;

    @NotNull
    private int diameter;
}
