package com.wsn.nac.Util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        LocalDateTime localDateTime = TimeFormatTransUtils.timestamp2localDateTime(timestamp);
        String format = localDateTime.plusHours(8).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        jsonGenerator.writeString(format);
    }
}
