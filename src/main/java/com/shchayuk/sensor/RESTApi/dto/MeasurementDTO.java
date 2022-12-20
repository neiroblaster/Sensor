package com.shchayuk.sensor.RESTApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shchayuk.sensor.RESTApi.models.Sensor;
import com.shchayuk.sensor.RESTApi.utils.CustomBooleanDeserializer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;

import java.time.LocalDateTime;

public class MeasurementDTO {

    @Min(value = -100, message = "The value must be greater than -100")
    @Max(value = 100, message = "The value must be less than 100")
    @NotNull(message = "The value must not be null or empty")
    private Double value;

//    аннотация @BooleanFlag работает почти также как Custom class, за
//    исключением: "raining": "123" - выдает ошибку, что не может спарсить
//    это значение. В случае с Custom class - выдает красивое сообщение:
//    raining must be only true or false
//    @BooleanFlag
    @NotNull(message = "The value must not be null or empty")
    @JsonDeserialize(using = CustomBooleanDeserializer.class)
    private Boolean raining;

    @JsonProperty("sensor")
    private SensorDTO sensorDTO;

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

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
