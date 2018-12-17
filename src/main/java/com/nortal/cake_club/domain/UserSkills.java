package com.nortal.cake_club.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserSkills.
 */
@Entity
@Table(name = "user_skills")
public class UserSkills implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "strength")
    private Long strength;

    @Column(name = "speed")
    private Long speed;

    @Column(name = "stamina")
    private Long stamina;

    @Column(name = "cooking")
    private Long cooking;

    @OneToOne    @JoinColumn(unique = true)
    private User user;

    public UserSkills() {

    }

    public UserSkills(Long strength, Long speed, Long stamina, Long cooking, User user) {
        this.strength = strength;
        this.speed = speed;
        this.stamina = stamina;
        this.cooking = cooking;
        this.user = user;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStrength() {
        return strength;
    }

    public UserSkills strength(Long strength) {
        this.strength = strength;
        return this;
    }

    public void setStrength(Long strength) {
        this.strength = strength;
    }

    public Long getSpeed() {
        return speed;
    }

    public UserSkills speed(Long speed) {
        this.speed = speed;
        return this;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getStamina() {
        return stamina;
    }

    public UserSkills stamina(Long stamina) {
        this.stamina = stamina;
        return this;
    }

    public void setStamina(Long stamina) {
        this.stamina = stamina;
    }

    public Long getCooking() {
        return cooking;
    }

    public UserSkills cooking(Long cooking) {
        this.cooking = cooking;
        return this;
    }

    public void setCooking(Long cooking) {
        this.cooking = cooking;
    }

    public User getUser() {
        return user;
    }

    public UserSkills user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSkills userSkills = (UserSkills) o;
        if (userSkills.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userSkills.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserSkills{" +
            "id=" + getId() +
            ", strength=" + getStrength() +
            ", speed=" + getSpeed() +
            ", stamina=" + getStamina() +
            ", cooking=" + getCooking() +
            "}";
    }
}
