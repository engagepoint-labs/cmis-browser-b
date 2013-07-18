package com.engagepoint.university.ep2013b.browser.component;

import javax.faces.context.FacesContext;
import java.util.Map;

// Helps to store parameters between requests

public class StateManager
{
    private Map<String, Object> params;
    private String clientId;

    public StateManager(FacesContext context, String clientId)
    {
        params = context.getViewRoot().getViewMap();
        this.clientId = clientId;
    }

    // Save parameter between requests
    public <T> void put(String name, T value)
    {
        params.put(clientId+name, value);
    }

    // Load parameter between requests
    public <T> T get(String name)
    {
        return (T)params.get(clientId+name);
    }

    public <T> T get(String name, T defaultValue)
    {
        T  value = (T)params.get(clientId+name);
        return (value == null) ? defaultValue : value;
    }
}
