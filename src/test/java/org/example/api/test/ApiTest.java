package org.example.api.test;



import com.gargoylesoftware.htmlunit.util.HeaderUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.example.api.test.util.HeaderProvider;
import org.junit.jupiter.api.Test;
import sun.rmi.log.LogInputStream;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {

    public static final String VALID_USER_NAME = "coding.challenge.login@upgrade.com";
    public static final String VALID_PASSWORD = "On$3XcgsW#9q";
    public static final String LOGIN_URL = "https://credapi.credify.tech/api/brportorch/v2/login";
    public static final String INVALID_USER = "usernamedoesnotexist";
    public static final String INVALID_PASSWORD = "inValid1$Passwd";

    @Test
    public void loginTest(){
        Map<String, String> payload = buildLoginPayload(VALID_USER_NAME, VALID_PASSWORD);
        HttpResponse<JsonNode> response = Unirest.post(LOGIN_URL)
                .headers(HeaderProvider.getLoginHeaders())
                .body(payload)
                .asJson();
        String productType;
        if (response.isSuccess()) {
            if (response.getBody() != null) {
                productType = response.getBody().getObject()
                        .getJSONArray("loansInReview")
                        .getJSONObject(0)
                        .getString("productType");
            } else {
                throw new RuntimeException("There is no response body");
            }
            assertEquals(200,response.getStatus());
            assertEquals("PERSONAL_LOAN",productType);
        } else {
            throw new RuntimeException("The login request was not successful, the server might not be available");
        }
    }

    @Test
    public void invalidLoginTest(){
        Map<String, String> payload = buildLoginPayload(INVALID_USER, INVALID_PASSWORD);
        HttpResponse<JsonNode> response = Unirest.post(LOGIN_URL)
                .headers(HeaderProvider.getLoginHeaders())
                .body(payload)
                .asJson();
        assertEquals(401,response.getStatus());
    }

    private static Map<String, String> buildLoginPayload(String username, String password) {
        Map<String, String> payload = new HashMap<>();
        payload.put("username", username);
        payload.put("password", password);
        return payload;
    }
}
