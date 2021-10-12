package com.wanghuiwen.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class MultipartFileSerializer extends StdSerializer<MultipartFile> {
    protected MultipartFileSerializer(Class<MultipartFile> t) {
        super(t);
    }

    public MultipartFileSerializer() {
        this(null);
    }

    @Override
    public void serialize(MultipartFile value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("file", value.getOriginalFilename());
        gen.writeEndObject();
    }
}
