package com.shchayuk.sensor.RESTApi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shchayuk.sensor.RESTApi.utils.CustomBooleanDeserializer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class MeasurementDTO {

    @Min(value = -100, message = "The value must be greater than -100")
    @Max(value = 100, message = "The value must be less than 100")
    @NotNull(message = "The value must not be null or empty")
    private Double value;

    @NotNull(message = "The value must not be null or empty")
    @JsonDeserialize(using = CustomBooleanDeserializer.class)
    private Boolean raining;

    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
