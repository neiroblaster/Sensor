package com.shchayuk.sensor.RESTApi.controllers;

import com.shchayuk.sensor.RESTApi.dto.SensorDTO;
import com.shchayuk.sensor.RESTApi.exceptions.MeasurementErrorResponse;
import com.shchayuk.sensor.RESTApi.exceptions.MeasurementException;
import com.shchayuk.sensor.RESTApi.models.Sensor;
import com.shchayuk.sensor.RESTApi.services.SensorService;
import com.shchayuk.sensor.RESTApi.utils.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.shchayuk.sensor.RESTApi.exceptions.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {

        Sensor sensor = convertToSensor(sensorDTO);

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);// отпр HTTP ответ с пустым телом и статусом 200
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handlerException (MeasurementException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
