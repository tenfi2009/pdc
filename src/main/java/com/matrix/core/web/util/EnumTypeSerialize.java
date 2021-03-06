package com.matrix.core.web.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.matrix.core.common.enums.EnumType;

public class EnumTypeSerialize extends JsonSerializer<EnumType> {  
	@Override
	public void serialize(EnumType value, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeString(value.getDisplayName());  
	}

}
