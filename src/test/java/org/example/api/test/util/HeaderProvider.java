package org.example.api.test.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeaderProvider {

    public static Map<String,String> getLoginHeaders(){
        Map<String,String> headers = new HashMap<>();
        headers.put("x-cf-source-id","coding-challenge");
        headers.put("x-cf-corr-id", UUID.randomUUID().toString());
        headers.put("Content-Type","application/json");
        return headers;
    }
}
