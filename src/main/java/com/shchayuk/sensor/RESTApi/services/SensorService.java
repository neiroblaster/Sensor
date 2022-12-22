package com.shchayuk.sensor.RESTApi.services;

import com.shchayuk.sensor.RESTApi.repositories.SensorRepository;
import com.shchayuk.sensor.RESTApi.models.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String name){
        return Optional.ofNullable(sensorRepository.findByName(name));
    }
}
