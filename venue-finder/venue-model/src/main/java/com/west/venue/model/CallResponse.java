package com.west.venue.model;

import java.util.List;

/**
 * Created by westp on 15/10/2016.
 */
public class CallResponse {

    private Meta meta;
    private List<Notifications> notifications;
    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
