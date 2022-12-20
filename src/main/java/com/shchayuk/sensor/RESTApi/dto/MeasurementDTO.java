package com.shchayuk.sensor.RESTApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shchayuk.sensor.RESTApi.models.Sensor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MeasurementDTO {

    @NotNull(message = "The value shouldn't be empty")
    @Min(value = -100)
    @Max(value = 100)
    private double value;

    @NotNull(message = "The value shouldn't be empty")
    private boolean raining;

    @JsonProperty("sensor")
    private SensorDTO sensorDTO;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
