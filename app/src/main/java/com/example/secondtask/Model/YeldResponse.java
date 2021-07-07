package com.example.secondtask.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YeldResponse {

    private List<Restaurant> businesses;
    @JsonProperty(value = "businesses")
    public List<Restaurant> getBusinesses() {
        return businesses;
    }
    @JsonProperty(value = "businesses")
    public void setBusinesses(List<Restaurant> businesses) {
        this.businesses = businesses;
    }
}
