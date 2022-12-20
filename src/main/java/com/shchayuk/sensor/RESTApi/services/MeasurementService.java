package com.shchayuk.sensor.RESTApi.services;

import com.shchayuk.sensor.RESTApi.models.Measurement;
import com.shchayuk.sensor.RESTApi.models.Sensor;
import com.shchayuk.sensor.RESTApi.repositories.MeasurementRepository;
import com.shchayuk.sensor.RESTApi.utils.MeasurementNotAddError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void add(Measurement measurement) {

        enrichMeasurement(measurement);

        Optional<Sensor> sensor = sensorService.findByName(measurement.getSensor().getName());

        if ((measurement.getSensor().getName()).isEmpty()) {
            throw new MeasurementNotAddError("The sensor's name shouldn't be empty");
        } else if (sensor.isPresent()) {
            measurement.getSensor().setId(sensor.get().getId());
        } else {
            throw new MeasurementNotAddError("Such sensor is not registered. " +
                    "Register this sensor before adding its measurement");
        }

        measurement.setSensor(measurement.getSensor());
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }
}
