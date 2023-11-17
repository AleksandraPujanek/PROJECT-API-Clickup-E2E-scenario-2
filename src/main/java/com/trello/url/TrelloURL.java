package com.trello.url;

public class TrelloURL {

    private static final String baseUrl = "https://api.trello.com/1";
    private static final String boardsUrl = "boards";
    private static final String listsUrl = "lists";
    private static final String cardsUrl = "cards";

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getBoardsUrl() {
        return getBaseUrl() + "/" + boardsUrl;
    }

    public static String getBoardUrl(String boardId) {
        return getBoardsUrl() + "/" + boardId;
    }

    public static String getListsUrl() {
        return getBaseUrl() + "/" + listsUrl;
    }

    public static String getListUrl(String listId) {
        return getListsUrl() + "/" + listId;
    }

    public static String getCardsUrl() {
        return getBaseUrl() + "/" + cardsUrl;
    }

    public static String getCardUrl(String cardId) {
        return getCardsUrl() + "/" + cardId;
    }
}
