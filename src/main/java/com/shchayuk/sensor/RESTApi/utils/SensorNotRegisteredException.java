package com.shchayuk.sensor.RESTApi.utils;

public class SensorNotRegisteredException extends RuntimeException {
    public SensorNotRegisteredException(String msg){
        super(msg);
    }
}
