package com.lezgo.gateway.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserInfoResponse {
    UserDetails userDetails;
    List<Event> events;
    List<Event> myEvents;
}
