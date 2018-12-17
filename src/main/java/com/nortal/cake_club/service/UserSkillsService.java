package com.nortal.cake_club.service;

import com.nortal.cake_club.config.Constants;
import com.nortal.cake_club.domain.Authority;
import com.nortal.cake_club.domain.User;
import com.nortal.cake_club.domain.UserSkills;
import com.nortal.cake_club.repository.AuthorityRepository;
import com.nortal.cake_club.repository.UserRepository;
import com.nortal.cake_club.repository.UserSkillsRepository;
import com.nortal.cake_club.security.AuthoritiesConstants;
import com.nortal.cake_club.security.SecurityUtils;
import com.nortal.cake_club.service.dto.UserDTO;
import com.nortal.cake_club.service.util.RandomUtil;
import com.nortal.cake_club.web.rest.errors.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserSkillsService {

    private final UserSkillsRepository userSkillsRepository;

    public UserSkillsService(UserSkillsRepository userSkillsRepository) {
        this.userSkillsRepository = userSkillsRepository;
    }

    public void save(User user, UserDTO userDTO) {

        /* todo: fill method body to save user skills  */

    }

}
