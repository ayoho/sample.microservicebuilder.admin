/*
 * Copyright 2016 Microprofile.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ibm.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.bootstrap.BootstrapData;
import com.ibm.model.User;

@ApplicationScoped
public class UserDAO {

    @Inject
    BootstrapData bootstrapData;

    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void initStore() {
        Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Initialise user DAO from bootstrap data");
        bootstrapData.getUsers()
                .forEach(bootstrap -> {
                    try {
                        final User sensitiveClass = new User(
                                bootstrap.getId(),
                                bootstrap.getEmployeeId(),
                                bootstrap.getUsername());

                        userMap.put(bootstrap.getId(), sensitiveClass);

                    } catch (final Exception e) {
                        System.out.println("Failed to parse bootstrap data: " + e.getMessage());
                    }

                });
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public Optional<User> findById(final String id) {
        return Optional.ofNullable(userMap.get(id));
    }

}
