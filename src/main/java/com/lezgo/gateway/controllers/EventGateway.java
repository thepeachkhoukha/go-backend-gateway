package com.lezgo.gateway.controllers;

import com.lezgo.gateway.models.events.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface EventGateway {

    @RequestMapping(value = "/saveevent", method = RequestMethod.POST)
    ResponseEntity<?> saveEvent(@RequestBody @Valid EventSaveRequest eventSaveRequest);

    @RequestMapping(value = "/reportevent", method = RequestMethod.POST)
    ResponseEntity<?> reportEvent(@RequestBody @Valid EventReportRequest eventReportRequest);

    @RequestMapping(value = "/nearme", method = RequestMethod.POST)
    ResponseEntity<?> getEvents(@RequestBody @Valid EventsFetchRequest eventsFetchRequest);

    @RequestMapping(value = "/joinevent", method = RequestMethod.POST)
    ResponseEntity<?> joinEvent(@RequestBody @Valid EventJoinRequest eventJoinRequest);

    @RequestMapping(value = "/participatedin", method = RequestMethod.GET)
    ResponseEntity<?> getEventsParticipatedInByOwnerUsername(@RequestParam @NotBlank String username);

    @RequestMapping(value = "/topevents", method = RequestMethod.POST)
    ResponseEntity<?> getTopEvents(@RequestBody @Valid EventTopRequest eventTopRequest);

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    ResponseEntity<?> getNumberEventsToday(@RequestParam @NotBlank String username);
}
