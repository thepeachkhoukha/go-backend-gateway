package com.lezgo.gateway.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class EventTopRequest {
    @NotBlank
    private String city;

    @NotBlank
    private String username;
}
