package com.west.venue.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by westp on 15/10/2016.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Venue {

    private String id;
    private Contact contact;
    private String name;
    private Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
