package com.ibm.bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;

import com.ibm.model.User;

/**
 * @author Heiko Braun
 * @since 15/09/16
 */
public class Parser {

    private final AtomicInteger id = new AtomicInteger(0);

    public BootstrapData parse(final URL jsonResource) {
        try {
            final JsonReaderFactory factory = Json.createReaderFactory(null);
            final JsonReader reader = factory.createReader(jsonResource.openStream());

            final JsonObject root = reader.readObject();
            final JsonArray userList = (JsonArray) root.get("users");

            final List<User> users = new LinkedList<>();

            for (final JsonValue item : userList) {
                JsonObject jsonItem = (JsonObject) item;
                final User userData = new User(String.valueOf(this.id.incrementAndGet()), jsonItem.getString("employeeId"), jsonItem.getString("username"));
                users.add(userData);
            }

            reader.close();

            return new BootstrapData(users);

        } catch (final IOException e) {
            throw new RuntimeException("Failed to parse sensitive data file.", e);
        }
    }

}
