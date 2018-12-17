package com.nortal.cake_club.repository;

import com.nortal.cake_club.domain.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

}
