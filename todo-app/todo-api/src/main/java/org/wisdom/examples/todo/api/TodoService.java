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
package org.wisdom.examples.todo.api;

import java.util.Collection;

/**
 * Created by subbu on 30/10/17.
 *
 * The TodoService contract to be implemented by Providers
 */
public interface TodoService<T> {

    /**
     * This method creates a Todo item in the repo
     * @param todoItem
     */
    void create(T todoItem);

    /**
     * This method retrieves a Todo item from the repo given the id
     * @param todoId
     * @return
     */
    T retrieve(String todoId);

    /**
     * This is an over-riden retrieve method to retrieve Todo items from the repo
     * given the page-num and page-size
     * @param todoId
     * @param pageNum
     * @param pageSize
     * @return
     */
    T retrieve(String todoId, int pageNum, int pageSize);

    /**
     * This method retrieves a Collection of all the Todo items from the repo
     * @return
     */
    Collection<T> all();

    /**
     * This method updates an existing Todo item in the repo
     * @param todoItem
     */
    void update(T todoItem);

    /**
     * This method removes a Todo item from the repo
     * @param todoId
     */
    void delete(String todoId);
}
