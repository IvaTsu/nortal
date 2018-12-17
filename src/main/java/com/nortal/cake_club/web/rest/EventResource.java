package com.nortal.cake_club.web.rest;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.nortal.cake_club.service.EventService;
import com.nortal.cake_club.service.dto.EventDTO;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class EventResource {
	
    private final Logger log = LoggerFactory.getLogger(UserResource.class);
	
	private final EventService eventService;
	
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * GET /events : get all events.
     *
     * @return the ResponseEntity with status 200 (OK) and with body all events
     */
    @GetMapping("/event")
    @Timed
    public ResponseEntity<List<EventDTO>> getAllEvents(@RequestParam(value = "userEvents", required = false) String userEvents) {

        /* todo: modify to take additional params (number of participants) */

        return new ResponseEntity<>(eventService.getAllEvents(null, Boolean.valueOf(userEvents)), HttpStatus.OK);
    }
    
    /**
     * GET /event/:id.
     *
     * @param id the id of the event to find
     * @return the ResponseEntity with status 200 (OK) and with body the "id" event, or with status 404 (Not Found)
     */
    @GetMapping("/event/{id}")
    @Timed
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long id) {
        log.debug("REST request to get Event : {}", id);
        return ResponseUtil.wrapOrNotFound(eventService.getEvent(id));
    }

    @PostMapping("event/single")
    @Timed
    public void createEvent(@RequestBody @Valid EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
    }

    @PostMapping("event/multiple")
    @Timed
    public void createEvents(@RequestBody @Valid EventDTO eventDTO) {
        eventService.createEvents(eventDTO);
    }

    /* todo: create DELETE endpoint */

}
