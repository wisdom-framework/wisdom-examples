package org.wisdom.examples.session.api;

/**
 * Created by subbu on 07/11/17.
 *
 * The Session API interface defining the contract for the implementation of
 * Session management.
 */
public interface Session {
    /**
     * This method returns an Object from the Session store given the
     * - sessionId and key.
     * @param id
     * @param name
     * @return
     */
    public Object get(String id, String name);

    /**
     * This method puts a key value pair into the Session store given the
     * - key, value and sessionId
     * @param id
     * @param name
     * @param value
     */
    public void put(String id, String name, Object value);

    /**
     * This method checks if a session exists
     * @return
     */
    public boolean exists(String id);

    /**
     * This method creates an entry in the Session Store and returns the sessionId
     * @return
     */
    public String create();

    /**
     * This method destroys a session from the Session Store
     * @param id
     */
    public void destroy(String id);

    /**
     * This method dumps the session.
     * @param id
     */
    public void dumpSession(String id);
}
