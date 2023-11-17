package com.trello.requests.board;

import com.trello.requests.BaseRequest;
import com.trello.url.TrelloURL;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {
    public static Response deleteBoardRequest(String boardId){
        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .delete(TrelloURL.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }

}
