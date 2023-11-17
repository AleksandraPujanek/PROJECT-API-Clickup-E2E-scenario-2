package com.trello.requests.list;

import com.trello.requests.BaseRequest;
import com.trello.url.TrelloURL;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateListRequest {
    public static Response createListRequest(Map<String, String> queryParams) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloURL.getListsUrl())
                .then()
                .extract()
                .response();
    }

}
