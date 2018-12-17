package com.nortal.cake_club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nortal.cake_club.domain.Event;

/**
 * Spring Data JPA repository for the Event entity.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

    @Query(value = "SELECT event.id, event.title, event.description, event.start_date AS date, jhi_user.login AS user FROM jhi_calendar_event event " +
        "INNER JOIN jhi_user_event user_event ON event.id = user_event.event_id " +
        "INNER JOIN jhi_user ON jhi_user.id = user_event.user_id " +
        "WHERE (jhi_user.id = :user_id OR :user_id IS NULL) AND (event.id = :event_id OR :event_id IS NULL) " +
        "ORDER BY event.id", nativeQuery = true)
    List<EventItem> findEvents(
        @Param("event_id") Long eventId,
        @Param("user_id") Long userId
    );
}
