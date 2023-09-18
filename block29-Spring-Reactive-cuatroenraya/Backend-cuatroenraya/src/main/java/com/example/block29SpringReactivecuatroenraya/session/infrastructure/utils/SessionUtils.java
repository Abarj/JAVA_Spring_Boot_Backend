package com.example.block29SpringReactivecuatroenraya.session.infrastructure.utils;

import com.example.block29SpringReactivecuatroenraya.player.entity.Player;
import com.example.block29SpringReactivecuatroenraya.session.entity.Session;
import com.example.block29SpringReactivecuatroenraya.session.infrastructure.enums.Status;
import lombok.Data;

import java.util.Optional;

@Data
public class SessionUtils {

    // Constantes para el número máximo de columnas, filas, la secuencia ganadora y el espacio vacío
    private static final int MAX_COLUMN = 7;
    private static final int MAX_ROW = 6;
    private static final String EMPTY_SPACE = "0";
    private static final int WINNING_SEQUENCE = 4;

    // Método para colocar una ficha en el tablero
    public static boolean putToken(Session session, Player player, int column) {
        // Verifica si hay espacio en la columna seleccionada
        Optional<int[]> coordinates = checkColumnSpace(session, column);

        // Si no hay espacio, imprime un mensaje y retorna false
        if (!coordinates.isPresent()) {
            System.out.println("There is no space in this column");
            return false;
        } else {
            // Si hay espacio, coloca la ficha del jugador en las coordenadas obtenidas
            session.getBoard()[coordinates.get()[0]][coordinates.get()[1]] = player.getUsername();
        }

        // Verifica si hay una secuencia ganadora en el tablero
        if (checkForWin(session, player.getUsername())) {
            // Si hay una secuencia ganadora, establece el estado de la sesión a WINNER_ONE o WINNER_TWO dependiendo del jugador
            if (session.getPlayerOne().equals(player.getUsername())) session.setStatus(Status.WINNER_ONE);
            if (session.getPlayerTwo().equals(player.getUsername())) session.setStatus(Status.WINNER_TWO);
            System.out.println("WINNER || Player: " + player.getUsername());
            return true;
        }

        // Si no hay una secuencia ganadora y el tablero está lleno, establece el estado de la sesión a TIE
        if (isBoardFull(session)) {
            session.setStatus(Status.TIE);
        }

        return false;
    }

    // Método para mostrar el tablero actual
    public static void showActualBoard(Session session) {
        for (int i = 0; i < session.getBoard().length; i++) {
            System.out.print("Row " + (i+1) + " || ");

            for (int j = 0; j < session.getBoard()[i].length; j++){
                System.out.print(session.getBoard()[i][j] + " | ");
            }

            System.out.print("\n");
        }
    }

    // Método para verificar si hay espacio en una columna
    private static Optional<int[]> checkColumnSpace(Session session, int column) {
        for (int i = session.getBoard().length - 1; i >= 0; i--){
            if (session.getBoard()[i][column].equals(EMPTY_SPACE)) {
                return Optional.of(new int[]{i, column});
            }
        }
        return Optional.empty();
    }

    // Método para verificar si hay una secuencia ganadora en el tablero
    private static boolean checkForWin(Session session, String playerName) {
        return checkHorizontal(session, playerName) || checkVertical(session, playerName) || checkDiagonal(session, playerName);
    }

    // Método para verificar si hay una secuencia ganadora en horizontal
    private static boolean checkHorizontal(Session session, String playerName) {
        for (int i = 0; i < session.getBoard().length; i++) {
            int count = 0;
            for (int j = 0; j < session.getBoard()[0].length; j++) {
                if (session.getBoard()[i][j].equals(playerName)) {
                    count++;
                    if (count == WINNING_SEQUENCE) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    // Método para verificar si hay una secuencia ganadora en vertical
    private static boolean checkVertical(Session session, String playerName) {
        for (int i = 0; i < session.getBoard()[0].length; i++) {
            int count = 0;
            for (int j = 0; j < session.getBoard().length; j++) {
                if (session.getBoard()[j][i].equals(playerName)) {
                    count++;
                    if (count == WINNING_SEQUENCE) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    // Método para verificar si hay una secuencia ganadora en diagonal
    private static boolean checkDiagonal(Session session, String playerName) {
        // Verifica las diagonales de izquierda a derecha, de arriba a abajo
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (isWinningSequence(session, playerName, i, j, 1, 1)) {
                    return true;
                }
            }
        }

        // Verifica las diagonales de derecha a izquierda, de arriba a abajo
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > 2; j--) {
                if (isWinningSequence(session, playerName, i, j, 1, -1)) {
                    return true;
                }
            }
        }

        // Verifica las diagonales de izquierda a derecha, de abajo a arriba
        for (int i = 5; i > 2; i--) {
            for (int j = 0; j < 4; j++) {
                if (isWinningSequence(session, playerName, i, j, -1, 1)) {
                    return true;
                }
            }
        }

        // Verifica las diagonales de derecha a izquierda, de abajo a arriba
        for (int i = 5; i > 2; i--) {
            for (int j = 6; j > 2; j--) {
                if (isWinningSequence(session, playerName, i, j, -1, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Método para verificar si hay una secuencia ganadora en una dirección dada
    private static boolean isWinningSequence(Session session, String playerName, int startRow, int startCol, int rowStep, int colStep) {
        for (int i = 0; i < 4; i++) {
            if (!session.getBoard()[startRow + i * rowStep][startCol + i * colStep].contains(playerName)) {
                return false;
            }
        }

        // Si hay una secuencia ganadora, establece el estado del juego a Ganador1 o Ganador2 dependiendo del jugador
        if (session.getPlayerOne().equals(playerName)) session.setStatus(Status.WINNER_ONE);
        if (session.getPlayerTwo().equals(playerName)) session.setStatus(Status.WINNER_TWO);

        System.out.println("WINNER || Player: " + playerName);

        return true;
    }

    // Método para verificar si el tablero está lleno
    private static boolean isBoardFull(Session session) {
        for (int i = 0; i < MAX_COLUMN; i++) {
            if (checkColumnSpace(session, i).isPresent()) {
                return false;
            }
        }
        return true;
    }
}