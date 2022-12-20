package com.shchayuk.sensor.RESTApi.utils;

import com.fasterxml.jackson.core.JsonParser;

public class MeasurementNotAddError extends RuntimeException {
    public MeasurementNotAddError (String msg){
        super(msg);
    }

    public MeasurementNotAddError(JsonParser jsonParser, String s, String booleanValue,
                                  Class<Boolean> booleanClass) {
        super(s);
    }
}
