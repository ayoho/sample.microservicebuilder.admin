package com.ibm.bootstrap;

import java.util.Collection;

import com.ibm.model.User;

/**
 * @author Heiko Braun
 * @since 15/09/16
 */
public class BootstrapData {

    private final Collection<User> users;

    BootstrapData(final Collection<User> users) {
        this.users = users;
    }

    public Collection<User> getUsers() {
        return users;
    }
}
