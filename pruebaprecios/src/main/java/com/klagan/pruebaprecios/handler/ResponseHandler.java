package com.klagan.pruebaprecios.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    private ResponseHandler() {
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", object);

        return new ResponseEntity<>(map, status);
    }
}