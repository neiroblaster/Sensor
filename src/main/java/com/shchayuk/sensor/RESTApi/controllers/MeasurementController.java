package com.shchayuk.sensor.RESTApi.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.shchayuk.sensor.RESTApi.dto.MeasurementDTO;
import com.shchayuk.sensor.RESTApi.dto.SensorDTO;
import com.shchayuk.sensor.RESTApi.models.Measurement;
import com.shchayuk.sensor.RESTApi.models.Sensor;
import com.shchayuk.sensor.RESTApi.services.MeasurementService;
import com.shchayuk.sensor.RESTApi.utils.MeasurementErrorResponse;
import com.shchayuk.sensor.RESTApi.utils.MeasurementNotAddError;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                      BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                List<FieldError> errors = bindingResult.getFieldErrors();
                StringBuilder sb = new StringBuilder();
                for (FieldError error : errors){
                    sb.append(error.getField()).append(" - ")
                            .append(error.getDefaultMessage()).append("; ");
                }
                throw new MeasurementNotAddError(sb.toString());
            }
            measurementService.add(convertToMeasurement(measurementDTO));
            return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementNotAddError e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler
//    public ResponseEntity<MeasurementErrorResponse> handlerException(InvalidFormatException e){
//        MeasurementErrorResponse response = new MeasurementErrorResponse(
//                e.getMessage(),
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
//    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
       Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
       measurement.setSensor(convertToSensor(measurementDTO.getSensorDTO()));
       return measurement;
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
