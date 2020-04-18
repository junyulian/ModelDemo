package com.jun.mvvmdemo.model;

import android.view.View;

import static com.jun.mvvmdemo.model.Player.O;
import static com.jun.mvvmdemo.model.Player.X;

public class Board {

    private enum GameState {IN_PROGRESS,FINISHED}

    private Cell[][] cells = new Cell[3][3];

    private Player winner;
    private GameState state;
    private Player currentTurn;

    public Board(){
        restart();
    }

    public void restart() {
        clearCells();
        winner = null;
        currentTurn = X;
        state = GameState.IN_PROGRESS;
    }

    private void clearCells() {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                cells[i][j] = new Cell();
            }
        }
    }


    public Player mark(int row, int col) {

        Player playerThatMoved = null;
        if(isValid(row,col)){
            cells[row][col].setValue(currentTurn);
            playerThatMoved = currentTurn;

            if(isWinningMoveByPlayer(currentTurn,row,col)){
                state = GameState.FINISHED;
                winner = currentTurn;
            }else{
                flipCurrentTurn();
            }

        }

        return playerThatMoved;
    }

    private boolean isValid(int row, int col) {
        if(state == GameState.FINISHED){
            return false;
        }else if(isOutOfBounds(row) || isOutOfBounds(col)){
            return false;
        }else if(isCellValueAlreadySet(row,col)){
            return false;
        }

        return true;
    }

    private boolean isOutOfBounds(int idx) {
        return idx<0 || idx>2;
    }

    private boolean isCellValueAlreadySet(int row, int col) {

        return cells[row][col].getValue() != null;
    }

    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {

        return (cells[currentRow][0].getValue() == player
                && cells[currentRow][1].getValue() == player
                && cells[currentRow][2].getValue() == player
                || cells[0][currentCol].getValue() == player
                && cells[1][currentCol].getValue() == player
                && cells[2][currentCol].getValue() == player
                || currentRow == currentCol
                && cells[0][0].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][2].getValue() == player
                || currentRow + currentCol == 2
                && cells[0][2].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][0].getValue() == player

        );
    }

    private void flipCurrentTurn() {
        currentTurn = currentTurn == X?O:X;
    }

    public Player getWiner() {

        return winner;
    }
}
