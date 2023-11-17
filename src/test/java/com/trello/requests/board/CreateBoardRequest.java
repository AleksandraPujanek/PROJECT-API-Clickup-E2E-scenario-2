package com.trello.requests.board;

import com.trello.requests.BaseRequest;
import com.trello.url.TrelloURL;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {
    public static Response createBoardRequest(Map<String, String> queryParams) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloURL.getBoardsUrl())
                .then()
                .extract()
                .response();
    }

}
