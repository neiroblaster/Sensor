package com.shchayuk.sensor.RESTApi.repositories;

import com.shchayuk.sensor.RESTApi.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
