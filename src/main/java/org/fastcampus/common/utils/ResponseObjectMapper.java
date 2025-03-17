package org.fastcampus.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fastcampus.common.ui.Response;

public class ResponseObjectMapper {

    private ResponseObjectMapper() {}

    private static final ObjectMapper objMapper = new ObjectMapper();

    public static Response toResponseObject(String response) {
        try {
            return objMapper.readValue(response, Response.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toString(Response<?> response) {
        try {
            return objMapper.writeValueAsString(response);
        } catch (Exception e) {
            return null;
        }
    }
}
