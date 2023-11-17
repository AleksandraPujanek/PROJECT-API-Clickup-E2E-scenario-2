package com.trello.requests.card;

import com.trello.requests.BaseRequest;
import com.trello.url.TrelloURL;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateCardRequest {
    public static Response createCardRequest(Map<String, String> queryParams) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloURL.getCardsUrl())
                .then()
                .extract()
                .response();
    }
}
