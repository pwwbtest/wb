package com.west.venue.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by westp on 15/10/2016.
 * This is partially populated only. More data is available in the feed and can be incorporated here if required.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contact {

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
