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
package org.wisdom.examples.todo.web.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.wisdom.api.annotations.QueryParameter;
import org.wisdom.examples.todo.entities.StatusEnum;
import org.wisdom.examples.todo.entities.TodoItem;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by subbu on 30/10/17.
 */
public class TodoDto {

    String id;

    private @NotNull
    String title;

    private String description;

    public TodoDto(@QueryParameter("id") String id, @QueryParameter("title") String title, @QueryParameter("desc") String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public TodoDto() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoItem generateTodoItem(){
        TodoItem todoItem = new TodoItem();
        if(id==null || id.isEmpty())
            todoItem.setId(String.valueOf(System.nanoTime()));
        else
            todoItem.setId(this.id);
        todoItem.setTitle(this.title);
        todoItem.setDescription(this.description);
        todoItem.setCreatedBy("Subbu");
        todoItem.setStatus(StatusEnum.CREATED);
        todoItem.setDateCreated(new SimpleDateFormat("dd/MMM/yyyy").format(new Date()));
        todoItem.setDateUpdated(new SimpleDateFormat("dd/MMM/yyyy").format(new Date()));
        return todoItem;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
