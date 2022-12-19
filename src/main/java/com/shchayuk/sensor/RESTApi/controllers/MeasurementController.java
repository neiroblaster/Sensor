package com.shchayuk.sensor.RESTApi.controllers;

import com.shchayuk.sensor.RESTApi.dto.MeasurementDTO;
import com.shchayuk.sensor.RESTApi.models.Measurement;
import com.shchayuk.sensor.RESTApi.services.MeasurementService;
import com.shchayuk.sensor.RESTApi.utils.MeasurementNotAddError;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
