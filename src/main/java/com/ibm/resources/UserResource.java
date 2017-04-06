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
package com.ibm.resources;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.model.User;
import com.ibm.persistence.UserDAO;

@ApplicationScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class UserResource {

    @Inject
    private UserDAO userDAO;

    // For use as a k8s readinessProbe for this service
    @GET
    @Path("/nessProbe")
    @Produces(MediaType.TEXT_PLAIN)
    public Response nessProbe() throws Exception {
        return Response.ok("User resource ready at " + Calendar.getInstance().getTime()).build();
    }

    @GET
    @Path("/all")
    public Response allUsers() {
        final List<User> allUsers = userDAO.getAllUsers();
        final GenericEntity<List<User>> entity = buildEntity(allUsers);
        return Response.ok(entity).build();
    }

    private GenericEntity<List<User>> buildEntity(final List<User> userList) {
        return new GenericEntity<List<User>>(userList) {
        };
    }

}
