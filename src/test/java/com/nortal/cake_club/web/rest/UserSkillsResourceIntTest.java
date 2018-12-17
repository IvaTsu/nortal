package com.nortal.cake_club.web.rest;

import com.nortal.cake_club.CakeClubApp;

import com.nortal.cake_club.domain.UserSkills;
import com.nortal.cake_club.repository.UserSkillsRepository;
import com.nortal.cake_club.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.nortal.cake_club.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserSkillsResource REST controller.
 *
 * @see UserSkillsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeClubApp.class)
public class UserSkillsResourceIntTest {

    private static final Long DEFAULT_STRENGTH = 1L;
    private static final Long UPDATED_STRENGTH = 2L;

    private static final Long DEFAULT_SPEED = 1L;
    private static final Long UPDATED_SPEED = 2L;

    private static final Long DEFAULT_STAMINA = 1L;
    private static final Long UPDATED_STAMINA = 2L;

    private static final Long DEFAULT_COOKING = 1L;
    private static final Long UPDATED_COOKING = 2L;

    @Autowired
    private UserSkillsRepository userSkillsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserSkillsMockMvc;

    private UserSkills userSkills;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserSkillsResource userSkillsResource = new UserSkillsResource(userSkillsRepository);
        this.restUserSkillsMockMvc = MockMvcBuilders.standaloneSetup(userSkillsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserSkills createEntity(EntityManager em) {
        UserSkills userSkills = new UserSkills()
            .strength(DEFAULT_STRENGTH)
            .speed(DEFAULT_SPEED)
            .stamina(DEFAULT_STAMINA)
            .cooking(DEFAULT_COOKING);
        return userSkills;
    }

    @Before
    public void initTest() {
        userSkills = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserSkills() throws Exception {
        int databaseSizeBeforeCreate = userSkillsRepository.findAll().size();

        // Create the UserSkills
        restUserSkillsMockMvc.perform(post("/api/user-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSkills)))
            .andExpect(status().isCreated());

        // Validate the UserSkills in the database
        List<UserSkills> userSkillsList = userSkillsRepository.findAll();
        assertThat(userSkillsList).hasSize(databaseSizeBeforeCreate + 1);
        UserSkills testUserSkills = userSkillsList.get(userSkillsList.size() - 1);
        assertThat(testUserSkills.getStrength()).isEqualTo(DEFAULT_STRENGTH);
        assertThat(testUserSkills.getSpeed()).isEqualTo(DEFAULT_SPEED);
        assertThat(testUserSkills.getStamina()).isEqualTo(DEFAULT_STAMINA);
        assertThat(testUserSkills.getCooking()).isEqualTo(DEFAULT_COOKING);
    }

    @Test
    @Transactional
    public void createUserSkillsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userSkillsRepository.findAll().size();

        // Create the UserSkills with an existing ID
        userSkills.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserSkillsMockMvc.perform(post("/api/user-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSkills)))
            .andExpect(status().isBadRequest());

        // Validate the UserSkills in the database
        List<UserSkills> userSkillsList = userSkillsRepository.findAll();
        assertThat(userSkillsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserSkills() throws Exception {
        // Initialize the database
        userSkillsRepository.saveAndFlush(userSkills);

        // Get all the userSkillsList
        restUserSkillsMockMvc.perform(get("/api/user-skills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userSkills.getId().intValue())))
            .andExpect(jsonPath("$.[*].strength").value(hasItem(DEFAULT_STRENGTH.intValue())))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED.intValue())))
            .andExpect(jsonPath("$.[*].stamina").value(hasItem(DEFAULT_STAMINA.intValue())))
            .andExpect(jsonPath("$.[*].cooking").value(hasItem(DEFAULT_COOKING.intValue())));
    }
    
    @Test
    @Transactional
    public void getUserSkills() throws Exception {
        // Initialize the database
        userSkillsRepository.saveAndFlush(userSkills);

        // Get the userSkills
        restUserSkillsMockMvc.perform(get("/api/user-skills/{id}", userSkills.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userSkills.getId().intValue()))
            .andExpect(jsonPath("$.strength").value(DEFAULT_STRENGTH.intValue()))
            .andExpect(jsonPath("$.speed").value(DEFAULT_SPEED.intValue()))
            .andExpect(jsonPath("$.stamina").value(DEFAULT_STAMINA.intValue()))
            .andExpect(jsonPath("$.cooking").value(DEFAULT_COOKING.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserSkills() throws Exception {
        // Get the userSkills
        restUserSkillsMockMvc.perform(get("/api/user-skills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserSkills() throws Exception {
        // Initialize the database
        userSkillsRepository.saveAndFlush(userSkills);

        int databaseSizeBeforeUpdate = userSkillsRepository.findAll().size();

        // Update the userSkills
        UserSkills updatedUserSkills = userSkillsRepository.findById(userSkills.getId()).get();
        // Disconnect from session so that the updates on updatedUserSkills are not directly saved in db
        em.detach(updatedUserSkills);
        updatedUserSkills
            .strength(UPDATED_STRENGTH)
            .speed(UPDATED_SPEED)
            .stamina(UPDATED_STAMINA)
            .cooking(UPDATED_COOKING);

        restUserSkillsMockMvc.perform(put("/api/user-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserSkills)))
            .andExpect(status().isOk());

        // Validate the UserSkills in the database
        List<UserSkills> userSkillsList = userSkillsRepository.findAll();
        assertThat(userSkillsList).hasSize(databaseSizeBeforeUpdate);
        UserSkills testUserSkills = userSkillsList.get(userSkillsList.size() - 1);
        assertThat(testUserSkills.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testUserSkills.getSpeed()).isEqualTo(UPDATED_SPEED);
        assertThat(testUserSkills.getStamina()).isEqualTo(UPDATED_STAMINA);
        assertThat(testUserSkills.getCooking()).isEqualTo(UPDATED_COOKING);
    }

    @Test
    @Transactional
    public void updateNonExistingUserSkills() throws Exception {
        int databaseSizeBeforeUpdate = userSkillsRepository.findAll().size();

        // Create the UserSkills

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserSkillsMockMvc.perform(put("/api/user-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSkills)))
            .andExpect(status().isBadRequest());

        // Validate the UserSkills in the database
        List<UserSkills> userSkillsList = userSkillsRepository.findAll();
        assertThat(userSkillsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserSkills() throws Exception {
        // Initialize the database
        userSkillsRepository.saveAndFlush(userSkills);

        int databaseSizeBeforeDelete = userSkillsRepository.findAll().size();

        // Get the userSkills
        restUserSkillsMockMvc.perform(delete("/api/user-skills/{id}", userSkills.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserSkills> userSkillsList = userSkillsRepository.findAll();
        assertThat(userSkillsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserSkills.class);
        UserSkills userSkills1 = new UserSkills();
        userSkills1.setId(1L);
        UserSkills userSkills2 = new UserSkills();
        userSkills2.setId(userSkills1.getId());
        assertThat(userSkills1).isEqualTo(userSkills2);
        userSkills2.setId(2L);
        assertThat(userSkills1).isNotEqualTo(userSkills2);
        userSkills1.setId(null);
        assertThat(userSkills1).isNotEqualTo(userSkills2);
    }
}
