package com.lezgo.gateway.models;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EventJoinRequest {
    @NotNull
    private int eventId;

    @NotBlank
    private String username;
}
