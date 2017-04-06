package com.ibm.bootstrap;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * @author Heiko Braun
 * @since 15/09/16
 */
@ApplicationScoped
public class BootstrapDataProducer {

    @Produces
    public BootstrapData load() {
        final String resourceName = "sensitiveData.json";
        final URL resource = Thread.currentThread().getContextClassLoader().getResource(resourceName);
        assert resource != null : "Failed to load '" + resourceName + "'";

        final Parser parser = new Parser();
        final BootstrapData data = parser.parse(resource);

        Logger.getLogger(BootstrapData.class.getName()).log(Level.INFO, "Data contains " + data.getUsers().size() + " user instances");

        return data;
    }

}
