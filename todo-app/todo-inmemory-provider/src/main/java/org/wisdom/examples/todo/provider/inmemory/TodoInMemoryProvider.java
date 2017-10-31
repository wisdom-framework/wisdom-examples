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
package org.wisdom.examples.todo.provider.inmemory;

import org.apache.felix.ipojo.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wisdom.examples.todo.api.TodoService;
import org.wisdom.examples.todo.entities.TodoItem;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by subbu on 30/10/17.
 */

@Component(name = "org.wisdom.examples.todo.inmemory.provider")
@Provides
@Instantiate
public class TodoInMemoryProvider implements TodoService<TodoItem> {

    Map<String, TodoItem> todoItems;

    private Logger logger = LoggerFactory.getLogger(TodoInMemoryProvider.class);

    @Validate
    public void activate() {
        todoItems = new ConcurrentHashMap<>();
        logger.info("The InMemoryProvider has been activated...");
    }

    @Invalidate
    public void deactivate() {
        todoItems = null;
        logger.info("The InMemoryProvider has been de-activated...");
    }

    /**
     * This method creates a Todo item in the repo
     *
     * @param todoItem
     */
    @Override
    public void create(TodoItem todoItem) {
        logger.info("Adding a new item to the repo");
        todoItems.put(todoItem.getId(), todoItem);
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
        return todoItems.get(todoId);
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
        return todoItems.values();
    }

    /**
     * This method updates an existing Todo item in the repo
     *
     * @param todoItem
     */
    @Override
    public void update(TodoItem todoItem) {
        logger.info("Updating an item in the repo");
        todoItems.put(todoItem.getId(), todoItem);
    }

    /**
     * This method removes a Todo item from the repo
     *
     * @param todoId
     */
    @Override
    public void delete(String todoId) {
        logger.info("Deleting an item from the repo");
        todoItems.remove(todoId);
    }
}
