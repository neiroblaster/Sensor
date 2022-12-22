package com.shchayuk.sensor.RESTApi.utils;

import com.shchayuk.sensor.RESTApi.models.Measurement;
import com.shchayuk.sensor.RESTApi.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (measurement.getSensor().getName() == null) {
            errors.rejectValue("sensor", "sensor must not be null");
        } else if (sensorService.findByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "Such sensor is not registered. " +
                    "Register this sensor before adding its measurement");
        }
    }


}
