package org.example.api.test;



import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {

    @Test
    public void loginTest(){
        HttpResponse<JsonNode> response = Unirest.post("https://credapi.credify.tech/api/brportorch/v2/login")
                .header("x-cf-source-id","coding-challenge")
                .header("x-cf-corr-id", UUID.randomUUID().toString())
                .header("Content-Type","application/json")
                .body("{\"username\":\"coding.challenge.login@upgrade.com\",\"password\":\"On$3XcgsW#9q\"}")
                .asJson();
        String productType = response.getBody().getObject()
                .getJSONArray("loansInReview")
                .getJSONObject(0)
                .getString("productType");
        assertEquals(200,response.getStatus());
        assertEquals("PERSONAL_LOAN",productType);



    }
}
