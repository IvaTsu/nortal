package com.nortal.cake_club.repository;

import java.util.Date;

public interface EventItem {

    Long getId();

    String getTitle();

    String getDescription();

    Date getDate();

    String getUser();

}
