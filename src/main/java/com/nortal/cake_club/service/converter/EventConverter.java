package com.nortal.cake_club.service.converter;

import com.nortal.cake_club.domain.Event;
import com.nortal.cake_club.domain.User;
import com.nortal.cake_club.repository.EventItem;
import java.util.ArrayList;
import java.util.List;
import com.nortal.cake_club.service.dto.EventDTO;

public class EventConverter {

    public static Event convertDTO(EventDTO eventDTO, User user) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setStartDate(eventDTO.getStart());
        event.setUser(user);
        return event;
    }

    public static List<EventDTO> convertItems(List<EventItem> items) {
        List<EventDTO> result = new ArrayList<>();

        EventDTO eventDTO = null;
        Long eventId = null;

        for (EventItem item : items) {
            if (eventId == null || !eventId.equals(item.getId())) {
                eventId = item.getId();
                eventDTO = getEventDTO(item);
                result.add(eventDTO);
            }
            eventDTO.getUsers().add(item.getUser());
        }
        return result;
    }

    private static EventDTO getEventDTO(EventItem item) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(item.getId());
        eventDTO.setTitle(item.getTitle());
        eventDTO.setDescription(item.getDescription());
        eventDTO.setStart(item.getDate());
        eventDTO.setUsers(new ArrayList<>());
        return eventDTO;
    }
}
