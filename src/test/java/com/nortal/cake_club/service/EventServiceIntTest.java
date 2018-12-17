package com.nortal.cake_club.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nortal.cake_club.CakeClubApp;
import com.nortal.cake_club.domain.Event;
import com.nortal.cake_club.domain.User;
import com.nortal.cake_club.domain.UserEvent;
import com.nortal.cake_club.repository.EventRepository;
import com.nortal.cake_club.repository.UserEventRepository;
import com.nortal.cake_club.repository.UserRepository;
import com.nortal.cake_club.service.dto.EventDTO;

/**
 * Test class for the EventService REST controller.
 *
 * @see EventService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeClubApp.class)
@Transactional
public class EventServiceIntTest {
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserEventRepository userEventRepository;
    @Mock
    private UserService userService;
    
    private EventService eventService;
	
    private Event event;
    private Event event2;
    
    @Before
    public void init() {
    	eventService = new EventService(eventRepository, userService, userEventRepository);
    	
    	User user = createUser("johndoe", "john", "doe");
        User user2 = createUser("janedoe", "jane", "doe");
		userRepository.saveAndFlush(user);
        userRepository.saveAndFlush(user2);
        
        event = createEvent(user, "Event", "Description");
        event2 = createEvent(user2, "Event2", "Description2");
		eventRepository.saveAndFlush(event);
		eventRepository.saveAndFlush(event2);
		
        userEventRepository.save(new UserEvent(user.getId(), event.getId()));
        userEventRepository.save(new UserEvent(user2.getId(), event2.getId()));
    }

    
    @Test
    @Transactional
    public void assertThatAllEventsReturned() {
    	List<EventDTO> allEvents = eventService.getAllEvents(null, false);
    	assertThat(allEvents.size()).isEqualTo(15);
    }
    
    @Test
    @Transactional
    public void assertThatCorrectEventReturnedByUser() {
    	when(userService.getUserWithAuthorities()).thenReturn(Optional.of(event.getUser()));
    	
    	List<EventDTO> events = eventService.getAllEvents(null, true);
    	assertThat(events.size()).isEqualTo(1);
    	assertThat(events.get(0).getId()).isEqualTo(event.getId());
    }
    
    @Test
    @Transactional
    public void assertThatCorrectEventReturnedById() {
    	Optional<EventDTO> eventById = eventService.getEvent(event.getId());
    	assertTrue(eventById.isPresent());
    	assertThat(eventById.get().getId()).isEqualTo(event.getId());
    }
    
    @Test
    @Transactional
    public void assertThatCreateEventSavesEvent() {
    	User user = createUser("johnsmith", "john", "smith");
        userRepository.saveAndFlush(user);
        when(userService.getUserWithAuthorities()).thenReturn(Optional.of(user));

        List<EventDTO> events = eventService.getAllEvents(null, true);
        assertThat(events.isEmpty());

        EventDTO newEvent = createEventDTO(user);
        eventService.createEvent(newEvent);
        
        events = eventService.getAllEvents(null, true);
        assertThat(!events.isEmpty());
        assertThat(events.size()).isEqualTo(1L);
        assertThat(events.get(0).getTitle()).isEqualTo(newEvent.getTitle());
    }


	private EventDTO createEventDTO(User user) {
		EventDTO newEvent = new EventDTO();
        newEvent.setDescription("Description");
        newEvent.setStart(new Date());
        newEvent.setTitle("Title");
        newEvent.setUserId(user.getId());
		return newEvent;
	}

	private User createUser(String login, String firstName, String lastName) {
		User user = new User();
		user.setLogin(login);
        user.setPassword(RandomStringUtils.random(60));
        user.setActivated(true);
        user.setEmail(login + "@localhost");
		user.setFirstName(firstName);
		user.setLastName(lastName);
        user.setImageUrl("http://placehold.it/50x50");
        user.setLangKey("en");
		return user;
	}
	
	private Event createEvent(User user, String title, String description) {
		Event event = new Event();
		event.setTitle(title);
		event.setDescription(description);
        event.setStartDate(new Date());
		event.setUser(user);
		return event;
	}

}
