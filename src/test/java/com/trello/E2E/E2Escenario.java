package com.trello.E2E;

import com.trello.properties.TrelloProperties;
import com.trello.requests.board.CreateBoardRequest;
import com.trello.requests.board.DeleteBoardRequest;
import com.trello.requests.card.CreateCardRequest;
import com.trello.requests.card.UpdateCardRequest;
import com.trello.requests.list.CreateListRequest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class E2Escenario {
    //    private static String boardId;
    private static List<String> boardIds = new ArrayList<>();
    private static List<String> listIds = new ArrayList<>();
    private static List<String> cardIds = new ArrayList<>();

    @DisplayName("Create two boards")
    @ParameterizedTest(name = "Board name: {0}")
    @Order(1)
    @MethodSource("sampleBoardData")
    void createNewBoardTest(String boardName) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response createBoardResponse = CreateBoardRequest.createBoardRequest(queryParams);

        Assertions.assertThat(createBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = createBoardResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);

        String boardId = json.getString("id");
        boardIds.add(boardId);
    }

    private static Stream<Arguments> sampleBoardData() {
        return Stream.of(
                Arguments.of("Test board 1"),
                Arguments.of("Test board 2")
        );
    }

    @DisplayName("Create two lists on existing board 1")
    @ParameterizedTest(name = "List name: {0}")
    @Order(2)
    @MethodSource("sampleListData")
    void createListsTest(String listName, String boardId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", listName);
        queryParams.put("idBoard", boardId);

        final Response createListResponse = CreateListRequest.createListRequest(queryParams);
        Assertions.assertThat(createListResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = createListResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(listName);

        String listId = json.getString("id");
        listIds.add(listId);
    }

    private static Stream<Arguments> sampleListData() {
        return Stream.of(
                Arguments.of("Lista testowa 1", boardIds.get(0)),
                Arguments.of("Lista testowa 2", boardIds.get(0))
        );
    }

    @DisplayName("Create a card on each existing list")
    @ParameterizedTest(name = "Card name: {0}")
    @Order(3)
    @MethodSource("sampleCardData")
    void createCardOnList1(String cardName, String listId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", cardName);
        queryParams.put("idList", listId);

        Response createCardResponse = CreateCardRequest.createCardRequest(queryParams);
        Assertions.assertThat(createCardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = createCardResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(cardName);
        String cardId = json.getString("id");
        cardIds.add(cardId);
    }

    private static Stream<Arguments> sampleCardData() {
        return Stream.of(
                Arguments.of("Test card 1", listIds.get(0)),
                Arguments.of("Test card 2", listIds.get(1))
        );
    }

    @Test
    @Order(4)
    void moveCard1ToList2() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idList", listIds.get(1));

        Response updateCardResponse = UpdateCardRequest.updateCardRequest(queryParams, cardIds.get(0));
        Assertions.assertThat(updateCardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = updateCardResponse.jsonPath();
        Assertions.assertThat(json.getString("idList")).isEqualTo(listIds.get(1));
    }

    @Test
    @Order(5)
    void addMemberToCard1() {
        String memberID = TrelloProperties.getMemberId();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idMembers", memberID);

        Response updateCardResponse = UpdateCardRequest.updateCardRequest(queryParams, cardIds.get(0));
        Assertions.assertThat(updateCardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = updateCardResponse.jsonPath();
        Assertions.assertThat(json.getString("idMembers[0]")).isEqualTo(memberID);
    }

    @Test
    @Order(6)
    void addDueToCard1() {
        String due = "2025-11-06T10:44:00.000Z";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("due", due);

        Response updateCardResponse = UpdateCardRequest.updateCardRequest(queryParams, cardIds.get(0));
        Assertions.assertThat(updateCardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = updateCardResponse.jsonPath();
        Assertions.assertThat(json.getString("due")).isEqualTo(due);
    }

    @Test
    @Order(7)
    void archiveCard1() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("closed", "true");

        Response updateCardResponse = UpdateCardRequest.updateCardRequest(queryParams, cardIds.get(0));
        Assertions.assertThat(updateCardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath json = updateCardResponse.jsonPath();
        Assertions.assertThat(json.getString("closed")).isEqualTo("true");
    }

    @Test
    @Order(8)
    void deleteBoard() {
        for (String boardId : boardIds) {
            Response deleteBoardResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
            Assertions.assertThat(deleteBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
            JsonPath json = deleteBoardResponse.jsonPath();
        }
    }
}
