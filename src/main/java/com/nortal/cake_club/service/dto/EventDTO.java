package com.nortal.cake_club.service.dto;

import com.nortal.cake_club.domain.User;
import com.nortal.cake_club.repository.EventItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nortal.cake_club.domain.Event;

/**
 * A DTO representing a event.
 */
public class EventDTO {

    private Long id;
    
    @NotBlank
    @Size(min = 1, max = 100)
    private String title;
    @Size(max = 10000)
    private String description;
    @NotNull
    private Date start;
    private String eventColor;
    private Long userId;
    private Date minDate;
    private Date maxDate;

    private List<String> users;
    
    public EventDTO() {
        // Empty constructor needed for Jackson.
    }

    public EventDTO(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.start = event.getStartDate();
        this.eventColor = null;
        this.userId = event.getUser().getId();
    }

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getStart() {
		return start;
	}

	public String getEventColor() {
		return eventColor;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStart(Date startDate) {
		this.start = startDate;
	}

	public void setEventColor(String eventColor) {
		this.eventColor = eventColor;
	}
    
	public void setUserId(Long userId) {
		this.userId = userId;
	}

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
