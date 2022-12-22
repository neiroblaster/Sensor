package com.shchayuk.sensor.RESTApi.repositories;

import com.shchayuk.sensor.RESTApi.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Sensor findByName(String name);
}
