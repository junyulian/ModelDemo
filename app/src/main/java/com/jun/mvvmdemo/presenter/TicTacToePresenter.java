package com.jun.mvvmdemo.presenter;

import com.jun.mvvmdemo.model.Board;
import com.jun.mvvmdemo.model.Player;
import com.jun.mvvmdemo.viewlayer.TicTacToeView;

public class TicTacToePresenter extends BasePresenter<TicTacToeView> {

    private Board model;

    public TicTacToePresenter(){
        model = new Board();
    }

    public void onButtonSelected(int row,int col){
        Player playerThatMoved = model.mark(row,col);
        if(playerThatMoved != null){
            getView().setButtonText(row,col,playerThatMoved.toString());
            if(model.getWiner() != null){
                getView().showWinner(playerThatMoved.toString());
            }
        }
    }

    public void onResetSelected(){
        model.restart();
        getView().clearWinnerDisplay();
        getView().clearButtons();

    }

}
