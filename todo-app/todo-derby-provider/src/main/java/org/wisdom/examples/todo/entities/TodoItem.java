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
package org.wisdom.examples.todo.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by subbu on 30/10/17.
 */

@Entity
@Table (name="todo")
@XmlRootElement
public class TodoItem {

    @Id
    private String id;
    private String title;
    private String description;
    private StatusEnum status;
    private String createdBy;
    private String dateCreated;
    private String dateUpdated;

    public TodoItem(){}

    public TodoItem(String id, String title, String description, String createdBy, String dateCreated, String dateUpdated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TodoItem todoItem = (TodoItem) o;

        return new EqualsBuilder()
                .append(id, todoItem.id)
                .append(title, todoItem.title)
                .append(description, todoItem.description)
                .append(status, todoItem.status)
                .append(createdBy, todoItem.createdBy)
                .append(dateCreated, todoItem.dateCreated)
                .append(dateUpdated, todoItem.dateUpdated)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(title)
                .append(description)
                .append(status)
                .append(createdBy)
                .append(dateCreated)
                .append(dateUpdated)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
