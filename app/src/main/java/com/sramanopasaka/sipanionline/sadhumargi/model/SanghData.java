package com.sramanopasaka.sipanionline.sadhumargi.model;

import java.util.List;

/**
 * Name    :   pranavjdev
 * Date   : 8/18/17
 * Email : pranavjaydev@gmail.com
 */

public class SanghData {

    private Hobby hobbies = null;

    public Hobby getHobbies() {
        return hobbies;
    }

    public void setHobbies(Hobby hobbies) {
        this.hobbies = hobbies;
    }

    private Services services=null;

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }


    private List<SocialRole> roles=null;

    public List<SocialRole> getSocialRole() {
        return roles;
    }

    public void setSocialRole(List<SocialRole> socialRole) {
        this.roles = socialRole;
    }
}
