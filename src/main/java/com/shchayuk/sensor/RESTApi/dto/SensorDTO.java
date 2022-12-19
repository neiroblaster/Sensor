package com.shchayuk.sensor.RESTApi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "The name shouldn't be empty")
    @Size(min = 3, max = 30,
            message = "The name should be from 3 to 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
