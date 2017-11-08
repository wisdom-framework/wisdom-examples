package org.wisdom.examples.session.provider.inmemory;

import org.apache.felix.ipojo.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.examples.session.api.Session;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by subbu on 07/11/17.
 */

@Component(name = "org.wisdom.examples.session.inmemory.provider")
@Provides
@Instantiate
public class InMemorySessionProvider implements Session {

    private static final Logger logger = LoggerFactory.getLogger(InMemorySessionProvider.class);

    private ConcurrentHashMap<String, SessionObject> sessionStore = null;

    @Validate
    public void activate() {
        sessionStore = new ConcurrentHashMap<>();
        logger.info("The InMemorySessionProvider has been activated...");
    }

    @Invalidate
    public void deactivate() {
        sessionStore = null;
        logger.info("The InMemorySessionProvider has been de-activated...");
    }

    /**
     * This method returns an Object from the Session store given the
     * - sessionId and key.
     * @param id
     * @param name
     * @return
     */
    @Override
    public Object get(String id, String name) {
        logger.info("retrieving the value for - {} from the in-memory session store for the session-id - {}", name, id);
        SessionObject sessionObject = sessionStore.get(id);
        return sessionObject.getEntries().get(name);
    }

    /**
     * This method puts a key value pair into the Session store given the
     * - key, value and sessionId
     * @param id
     * @param name
     * @param value
     */
    @Override
    public void put(String id, String name, Object value) {
        logger.info("putting the value - {} for - {} to the in-memory session store for the session-id - {}", name, id);
        SessionObject sessionObject = sessionStore.get(id);
        sessionObject.getEntries().put(name, value);
    }

    /**
     * This method checks if a session exists
     * @return
     */
    public boolean exists(String id) {
        logger.info("validating if the session with id - {} exists", id);
        return sessionStore.contains(id);
    }

    /**
     * This method creates an entry in the Session Store and returns the sessionId
     * @return
     */
    @Override
    public String create() {
        String sessionId = String.valueOf(System.nanoTime());
        logger.info("creatign a new in the in-memory session store and the session-id is - {}", sessionId);
        sessionStore.put(sessionId, new SessionObject());
        return sessionId;
    }

    /**
     * This method destroys a session from the Session Store
     * @param id
     */
    @Override
    public void destroy(String id) {
        logger.info("removing the session from the in-memory session store with the session-id is - {}", id);
        sessionStore.remove(id);
    }

    /**
     * This method dumps the session.
     * @param id
     */
    @Override
    public void dumpSession(String id) {
        logger.info("the session from the in-memory session store with the session-id - {} is - \n{}", id, sessionStore.get(id).toString());
    }
}
