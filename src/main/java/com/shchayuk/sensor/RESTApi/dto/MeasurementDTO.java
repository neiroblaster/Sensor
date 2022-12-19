package com.shchayuk.sensor.RESTApi.dto;

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

    @NotEmpty(message = "The value shouldn't be empty")
    private boolean raining;

    @NotEmpty(message = "The value shouldn't be empty")
    private Sensor sensor;

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
