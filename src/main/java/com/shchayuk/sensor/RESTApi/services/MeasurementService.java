package com.shchayuk.sensor.RESTApi.services;

import com.shchayuk.sensor.RESTApi.models.Measurement;
import com.shchayuk.sensor.RESTApi.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void add(Measurement measurement) {

        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());

        measurementRepository.save(measurement);
    }

}
