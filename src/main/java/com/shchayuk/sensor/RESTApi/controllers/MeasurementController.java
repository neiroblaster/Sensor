package com.shchayuk.sensor.RESTApi.controllers;

import com.shchayuk.sensor.RESTApi.dto.MeasurementDTO;
import com.shchayuk.sensor.RESTApi.dto.MeasurementResponse;
import com.shchayuk.sensor.RESTApi.models.Measurement;
import com.shchayuk.sensor.RESTApi.services.MeasurementService;
import com.shchayuk.sensor.RESTApi.exceptions.MeasurementErrorResponse;
import com.shchayuk.sensor.RESTApi.exceptions.MeasurementException;
import com.shchayuk.sensor.RESTApi.utils.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.shchayuk.sensor.RESTApi.exceptions.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public MeasurementResponse getAllMeasurements(){
        List<Measurement> measurements = measurementService.findAll();
        return new MeasurementResponse(measurements.stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public HashMap<String, Long> getRainyDaysCount(){
        Long rainyDaysCount = measurementService.findAll().stream().filter(Measurement::getRaining).count();
        HashMap<String, Long> rainyDays = new HashMap<>();
        rainyDays.put("rainyDays", rainyDaysCount);
        return rainyDays;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                      BindingResult bindingResult){
        Measurement measurement = convertToMeasurement(measurementDTO);

            measurementValidator.validate(measurement, bindingResult);

            if(bindingResult.hasErrors())
                returnErrorsToClient(bindingResult);

            measurementService.add(measurement);
            return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
       return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }


}
