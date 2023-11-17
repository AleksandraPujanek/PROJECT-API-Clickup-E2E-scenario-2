package com.trello.requests.board;

import com.trello.requests.BaseRequest;
import com.trello.url.TrelloURL;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReadBoardRequest {
    public static Response readBoardRequest(String boardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .get(TrelloURL.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }

}
