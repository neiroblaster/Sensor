package com.shchayuk.sensor.RESTApi.controllers;

import com.shchayuk.sensor.RESTApi.dto.SensorDTO;
import com.shchayuk.sensor.RESTApi.models.Sensor;
import com.shchayuk.sensor.RESTApi.services.SensorService;
import com.shchayuk.sensor.RESTApi.utils.SensorErrorResponse;
import com.shchayuk.sensor.RESTApi.utils.SensorNotRegisteredException;
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
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                sb.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new SensorNotRegisteredException(sb.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));
        // отправляем HTTP ответ с пустым телом и статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handlerException (SensorNotRegisteredException e){
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
