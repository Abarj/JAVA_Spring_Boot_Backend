package com.example.enRaya.Backend.session.infrastructure.utils;

import com.example.enRaya.Backend.player.entity.Player;
import com.example.enRaya.Backend.session.entity.Session;
import com.example.enRaya.Backend.session.infrastructure.enums.Direction;
import com.example.enRaya.Backend.session.infrastructure.enums.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionUtils {
    private static final int BOARD_SIZE = 7;
    private static final int WINNING_COUNT = 4;
    private static final String EMPTY_SPACE = "0";

    public static boolean putToken(Session session, Player player, int column) {
        int[] coordinates = checkColumnSpace(session, column);
        if (coordinates[0] == BOARD_SIZE && coordinates[1] == BOARD_SIZE) {
            log.info("There is no space in this column");
        } else {
            session.getBoard()[coordinates[0]][coordinates[1]] = player.getUsername();
        }
        checkTables(session);
        return checkBoard(session, player.getUsername());
    }

    private static void checkTables(Session session){
        boolean espacio=false;
        for(int i=0;i<BOARD_SIZE;i++){
            int[] coordinates = checkColumnSpace(session,i);
            if (coordinates[0] != BOARD_SIZE && coordinates[1] != BOARD_SIZE) {
                espacio=true;
                break;
            }
        }
        if (!espacio) session.setStatus(Status.TIE);
    }

    public static int[] checkColumnSpace(Session session, int column) {
        int[] coordinates = new int[2];
        coordinates[0] = BOARD_SIZE;
        coordinates[1] = BOARD_SIZE;

        for (int i = session.getBoard().length - 1; i >= 0; i--) {
            if (session.getBoard()[i][column].equals(EMPTY_SPACE)) {
                coordinates[0] = i;
                coordinates[1] = column;
                break;
            }
        }

        log.info("Coordinates: " + coordinates[0] + " " + coordinates[1]);
        return coordinates;
    }

    public static boolean checkBoard(Session session, String playerName) {
        return checkHorizontal(session, playerName) && checkVertical(session, playerName) && checkDiagonal(session, playerName);
    }

    private static boolean checkHorizontal(Session session, String playerName) {
        return checkWin(session, playerName, Direction.HORIZONTAL);
    }

    private static boolean checkVertical(Session session, String playerName) {
        return checkWin(session, playerName, Direction.VERTICAL);
    }

    private static boolean checkDiagonal(Session session, String playerName) {
        return checkWin(session, playerName, Direction.DIAGONAL);
    }

    private static boolean checkWin(Session session, String playerName, Direction direction) {
        int counter = 0;
        for (int i = 0; i < session.getBoard().length; i++) {
            for (int j = 0; j < session.getBoard()[i].length; j++) {
                if (session.getBoard()[i][j].equals(playerName)) {
                    counter++;
                    if (counter >= WINNING_COUNT) {
                        log.info("WINNER in " + direction + ": " + (i+1) + " || Player: " + playerName);
                        updateGameStatus(session, playerName);
                        return false;
                    }
                } else if (!session.getBoard()[i][j].equals(EMPTY_SPACE)) {
                    counter = 0;
                }
            }
            counter = 0;
        }
        return true;
    }

    private static void updateGameStatus(Session session, String playerName) {
        if (session.getPlayerOne().equals(playerName)) session.setStatus(Status.WINNER_ONE);
        if (session.getPlayerTwo().equals(playerName)) session.setStatus(Status.WINNER_TWO);
    }
}