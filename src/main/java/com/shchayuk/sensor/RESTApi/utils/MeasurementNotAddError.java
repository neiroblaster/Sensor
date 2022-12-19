package com.shchayuk.sensor.RESTApi.utils;

public class MeasurementNotAddError extends RuntimeException {
    public MeasurementNotAddError (String msg){
        super(msg);
    }
}
