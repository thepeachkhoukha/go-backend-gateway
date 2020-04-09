package com.lezgo.gateway.models.user;

import com.lezgo.gateway.models.events.Event;
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
