package com.shchayuk.sensor.RESTApi.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;

public class CustomBooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String booleanValue = jsonParser.getText();
        if(!booleanValue.equals("true") && !booleanValue.equals("false")){
            // также работает, как и кастомная ошибка, но выдает некрасивый результат:
//            "message": "raining must be only true or false\n at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 3, column: 17] (through reference chain: com.shchayuk.sensor.RESTApi.dto.MeasurementDTO[\"raining\"])",
//                    "timestamp": 1671521672220
//            throw new InvalidFormatException(jsonParser, "raining must be only true or false",
//                    booleanValue, Boolean.class);
            // выдает красивый результат: "raining must be only true or false"
            throw new MeasurementNotAddError(jsonParser, "raining must be only true or false",
                    booleanValue, Boolean.class);
        }
        return Boolean.valueOf(booleanValue);
    }
}
