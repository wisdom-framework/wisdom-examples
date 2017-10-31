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
package org.wisdom.examples.todo.web.controllers;

import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.*;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;
import org.wisdom.examples.todo.api.TodoService;
import org.wisdom.examples.todo.entities.TodoItem;
import org.wisdom.examples.todo.web.dtos.TodoDto;

/**
 * Created by subbu on 30/10/17.
 *
 * This is the TodoController.
 * It provides all the actions required for the todo-app
 */
@Controller
public class TodoController extends DefaultController {

    /**
     * Injects a template named 'todo'.
     */
    @View("todo")
    Template todo;

    @Requires
    TodoService<TodoItem> todoService;

    /**
     * The action method returning the todo page.
     * It handles HTTP GET request on the "/todo" URL.
     *
     * @return the todo page
     */
    @Route(method = HttpMethod.GET, uri = "/todo")
    public Result todoView() {
        return ok(render(todo,"todoLst", todoService.all()));
    }

    /**
     * The action to handle the create action of todo obects
     * It handles HTTP GET request on the "/todo/create" URL.
     *
     * @return the todo page
     */
    @Route(method = HttpMethod.POST, uri = "/todo/create")
    public Result createTodo(@Body TodoDto todoDto) {
        logger().info("todoDto {}", todoDto.toString());
        todoService.create(todoDto.generateTodoItem());
        return redirect("/todo");
    }

    /**
     * The action to handle the delete action of todo obects
     * It handles HTTP GET request on the "/todo/delete" URL.
     *
     * @return the todo page
     */
    @Route(method = HttpMethod.GET, uri = "/todo/delete")
    public Result todoPost(@QueryParameter("id") String id) {
        todoService.delete(id);
        return redirect("/todo");
    }

    /**
     * The action to handle the update action of todo obects
     * It handles HTTP GET request on the "/todo/update" URL.
     *
     * @return the welcome page
     */
    @Route(method = HttpMethod.POST, uri = "/todo/update")
    public Result updateTodo(@Body TodoDto todoDto) {
        logger().info("todoDto {}", todoDto.toString());
        todoService.update(todoDto.generateTodoItem());
        return redirect("/todo");
    }

}
