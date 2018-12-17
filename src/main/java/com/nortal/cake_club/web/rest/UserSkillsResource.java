package com.nortal.cake_club.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.nortal.cake_club.domain.UserSkills;
import com.nortal.cake_club.repository.UserSkillsRepository;
import com.nortal.cake_club.web.rest.errors.BadRequestAlertException;
import com.nortal.cake_club.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserSkills.
 */
@RestController
@RequestMapping("/api")
public class UserSkillsResource {

    private final Logger log = LoggerFactory.getLogger(UserSkillsResource.class);

    private static final String ENTITY_NAME = "userSkills";

    private final UserSkillsRepository userSkillsRepository;

    public UserSkillsResource(UserSkillsRepository userSkillsRepository) {
        this.userSkillsRepository = userSkillsRepository;
    }

    /**
     * POST  /user-skills : Create a new userSkills.
     *
     * @param userSkills the userSkills to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userSkills, or with status 400 (Bad Request) if the userSkills has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-skills")
    @Timed
    public ResponseEntity<UserSkills> createUserSkills(@RequestBody UserSkills userSkills) throws URISyntaxException {
        log.debug("REST request to save UserSkills : {}", userSkills);
        if (userSkills.getId() != null) {
            throw new BadRequestAlertException("A new userSkills cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserSkills result = userSkillsRepository.save(userSkills);
        return ResponseEntity.created(new URI("/api/user-skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-skills : Updates an existing userSkills.
     *
     * @param userSkills the userSkills to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userSkills,
     * or with status 400 (Bad Request) if the userSkills is not valid,
     * or with status 500 (Internal Server Error) if the userSkills couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-skills")
    @Timed
    public ResponseEntity<UserSkills> updateUserSkills(@RequestBody UserSkills userSkills) throws URISyntaxException {
        log.debug("REST request to update UserSkills : {}", userSkills);
        if (userSkills.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserSkills result = userSkillsRepository.save(userSkills);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userSkills.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-skills : get all the userSkills.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userSkills in body
     */
    @GetMapping("/user-skills")
    @Timed
    public List<UserSkills> getAllUserSkills() {
        log.debug("REST request to get all UserSkills");
        return userSkillsRepository.findAll();
    }

    /**
     * GET  /user-skills/:id : get the "id" userSkills.
     *
     * @param id the id of the userSkills to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userSkills, or with status 404 (Not Found)
     */
    @GetMapping("/user-skills/{id}")
    @Timed
    public ResponseEntity<UserSkills> getUserSkills(@PathVariable Long id) {
        log.debug("REST request to get UserSkills : {}", id);
        Optional<UserSkills> userSkills = userSkillsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userSkills);
    }

    /**
     * DELETE  /user-skills/:id : delete the "id" userSkills.
     *
     * @param id the id of the userSkills to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-skills/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserSkills(@PathVariable Long id) {
        log.debug("REST request to delete UserSkills : {}", id);

        userSkillsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
