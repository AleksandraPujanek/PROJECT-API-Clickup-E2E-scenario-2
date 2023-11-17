package com.trello.requests.card;

import com.trello.requests.BaseRequest;
import com.trello.url.TrelloURL;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateCardRequest {
    public static Response updateCardRequest(Map<String, String> queryParams, String cardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .put(TrelloURL.getCardUrl(cardId))
                .then()
                .extract()
                .response();
    }
}
