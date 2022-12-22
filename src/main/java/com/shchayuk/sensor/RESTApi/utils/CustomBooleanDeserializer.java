package com.shchayuk.sensor.RESTApi.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.shchayuk.sensor.RESTApi.exceptions.MeasurementException;

import java.io.IOException;

public class CustomBooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String booleanValue = jsonParser.getText();
        if(!booleanValue.equals("true") && !booleanValue.equals("false")){
            throw new MeasurementException("raining must be only true or false");
        }
        return Boolean.valueOf(booleanValue);
    }
}
