package org.wisdom.examples.session.provider.inmemory;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by subbu on 07/11/17.
 */
public class SessionObject {

    private ConcurrentHashMap<String, Object> entries = null;

    public SessionObject() {
        entries = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, Object> getEntries() {
        return entries;
    }

    public void setEntries(ConcurrentHashMap<String, Object> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
