package com.lezgo.gateway.controllers;

import com.lezgo.gateway.models.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class EventGatewayImplementation implements EventGateway {

    @Autowired
    private EventGatewayService eventGatewayService;

    @RequestMapping(value = "/saveevent", method = RequestMethod.POST)
    public ResponseEntity<?> saveEvent(@RequestBody EventSaveRequest eventSaveRequest) {
        return eventGatewayService.saveEvent(eventSaveRequest);
    }

    @RequestMapping(value = "/reportevent", method = RequestMethod.POST)
    public ResponseEntity<?> reportEvent(@RequestBody @Valid EventReportRequest eventReportRequest) {
        return eventGatewayService.reportEvent(eventReportRequest);
    }

    @RequestMapping(value = "/nearme", method = RequestMethod.POST)
    public ResponseEntity<?> getEvents(@RequestBody @Valid EventsFetchRequest eventsFetchRequest) {
        return eventGatewayService.getEvents(eventsFetchRequest);
    }

    @RequestMapping(value = "/joinevent", method = RequestMethod.POST)
    public ResponseEntity<?> joinEvent(@RequestBody @Valid EventJoinRequest eventJoinRequest) {
        return eventGatewayService.joinEvent(eventJoinRequest);
    }

    @RequestMapping(value = "/participatedin", method = RequestMethod.GET)
    public ResponseEntity<?> getEventsParticipatedInByOwnerUsername(@RequestParam @NotBlank String username) {
        return ResponseEntity.ok().body(eventGatewayService.getEventsParticipatedInByOwnerUsername(username));
    }

    @RequestMapping(value = "/topevents", method = RequestMethod.POST)
    public ResponseEntity<?> getTopEvents(@RequestBody @Valid EventTopRequest eventTopRequest) {
        return eventGatewayService.getTopEvent(eventTopRequest);
    }

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    public ResponseEntity<?> getNumberEventsToday(@RequestParam @NotBlank String username){
        return ResponseEntity.ok().body(eventGatewayService.getNumberEventsToday(username));
    }
}
