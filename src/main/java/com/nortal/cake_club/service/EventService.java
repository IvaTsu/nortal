package com.nortal.cake_club.service;

import static com.nortal.cake_club.service.converter.EventConverter.convertDTO;
import static com.nortal.cake_club.service.converter.EventConverter.convertItems;

import com.nortal.cake_club.domain.Event;
import com.nortal.cake_club.domain.User;
import com.nortal.cake_club.domain.UserEvent;
import com.nortal.cake_club.repository.UserEventRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nortal.cake_club.repository.EventRepository;
import com.nortal.cake_club.service.dto.EventDTO;


/**
 * Service class for managing events.
 */
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    private final UserService userService;

    private final UserEventRepository userEventRepository;

    public EventService(EventRepository eventRepository, UserService userService, UserEventRepository userEventRepository) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.userEventRepository = userEventRepository;
    }

    @Transactional(readOnly = true)
    public List<EventDTO> getAllEvents(Long eventId, Boolean userEvents) {
        Long userId = userEvents ? userService.getUserWithAuthorities().get().getId() : null;

        /* todo: with additional params (number of event participants) extract the right events */

        return convertItems(eventRepository.findEvents(eventId, userId));
    }

    @Transactional(readOnly = true)
    public Optional<EventDTO> getEvent(Long id) {
        List<EventDTO> events = getAllEvents(id, false);
        return events.stream().findFirst();
    }

    public void createEvent(EventDTO eventDTO) {
        User currentUser = userService.getUserWithAuthorities().get();
        Event event = eventRepository.save(convertDTO(eventDTO, currentUser));
        userEventRepository.save(new UserEvent(currentUser.getId(), event.getId()));
    }

    public void createEvents(EventDTO eventDTO) {
        /* todo: create algorithm which finds the first perfect event date (between user defined min and max date) and saves it for all (possible) participants */

        /* Additional information:
         *
         * The perfect date has two definitions in this context:
         * (a) a date where every user can participate OR
         * (b) a date where the most number on participants can partake
         *
         * You can implement either a or b (option b is a little bit harder and thus it gives more points)
         *
         * Event is saved to jhi_calendar_event (EventRepository) (NB! jhi_calendar_event has a field 'user_id' - this is for event creator)
         * Event-users relations are saved to jhi_user_event (UserEventRepository)
         *
         * NB! You can use UserService.getAllRegularUsers() to get all users
         */
    }

    /* todo: create event DELETE method (nb! don't forget about relations between db-tables jhi_calendar_event (EventRepository) and jhi_user_event (UserEventRepository)) 
     * also make sure only the creator of the event can delete it
     * */

}
