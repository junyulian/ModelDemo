package com.jun.mvvmdemo.viewlayer;

public interface TicTacToeView extends BaseView{
    void showWinner(String winnerLabel);
    void clearWinnerDisplay();
    void clearButtons();
    void setButtonText(int row,int col,String text);
}
