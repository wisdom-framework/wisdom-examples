/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2017 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.wisdom.examples.todo.provider.derby;

import org.apache.felix.ipojo.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.examples.todo.api.TodoService;
import org.wisdom.examples.todo.entities.TodoItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

/**
 * Created by subbu on 30/10/17.
 */

@Component(name = "org.wisdom.examples.todo.derby.provider")
@Provides
@Instantiate
public class TodoDerbyProvider implements TodoService<TodoItem> {

    private Logger logger = LoggerFactory.getLogger(TodoDerbyProvider.class);

    @Requires
    private EntityManagerFactory emf;

    private EntityManager em;

    @Validate
    public void activate() {
        try {
            logger.info("About to create the EntityManager");
            if(emf == null || !em.isOpen()) {
                logger.info("Seems like the EntityManagerFactory is not initialized yet....");
                emf = Persistence.createEntityManagerFactory("todo-pu");
                logger.info("Created the EntityManagerFactory....");
            }
            if(em == null) {
                em = emf.createEntityManager();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        logger.info("The DerbyProvider has been activated...");
    }

    @Invalidate
    public void deactivate() {
        try {
            em.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.info("The DerbyProvider has been de-activated...");
    }

    /**
     * This method creates a Todo item in the repo
     *
     * @param todoItem
     */
    @Override
    public void create(TodoItem todoItem) {
        logger.info("Adding a new item to the repo");
        try {
            em.getTransaction().begin();
            em.persist(todoItem);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.getTransaction().commit();
        }
    }

    /**
     * This method retrieves a Todo item from the repo given the id
     *
     * @param todoId
     * @return
     */
    @Override
    public TodoItem retrieve(String todoId) {
        logger.info("Retrieving an item from the repo");
        TodoItem todoItem = null;
        try {
            todoItem = em.find(TodoItem.class, todoId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return todoItem;
    }

    /**
     * This is an over-riden retrieve method to retrieve Todo items from the repo
     * given the page-num and page-size
     *
     * @param todoId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public TodoItem retrieve(String todoId, int pageNum, int pageSize) {
        //FIXME - This needs to be fixed to work on the pagination logic
        return null;
    }

    /**
     * This method retrieves a Collection of all the Todo items from the repo
     *
     * @return
     */
    @Override
    public Collection<TodoItem> all() {
        logger.info("Retrieving all the items from the repo");
        List<TodoItem> todoItems = null;
        try {
            todoItems = (List<TodoItem>)em.createQuery("Select todoItem from TodoItem todoItem").getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return todoItems;
    }

    /**
     * This method updates an existing Todo item in the repo
     *
     * @param todoItem
     */
    @Override
    public void update(TodoItem todoItem) {
        logger.info("Updating an item in the repo");
        try {
            em.getTransaction().begin();
            em.persist(todoItem);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.getTransaction().commit();
        }
    }

    /**
     * This method removes a Todo item from the repo
     *
     * @param todoId
     */
    @Override
    public void delete(String todoId) {
        logger.info("Deleting an item from the repo");
        try {
            em.getTransaction().begin();
            em.remove(em.find(TodoItem.class, todoId));
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.getTransaction().commit();
        }
    }
}
