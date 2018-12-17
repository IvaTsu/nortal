package com.nortal.cake_club.repository;

import com.nortal.cake_club.domain.User;
import com.nortal.cake_club.domain.UserSkills;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserSkills entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserSkillsRepository extends JpaRepository<UserSkills, Long> {

    UserSkills findOneByUser(User user);

}
