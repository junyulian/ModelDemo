package com.jun.mvvmdemo.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;

import com.jun.mvvmdemo.model.Board;
import com.jun.mvvmdemo.model.Player;

public class TicTacToeViewModel {

    private Board model;

    public final ObservableArrayMap<String,String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public TicTacToeViewModel(){
        model = new Board();
    }

    public void onResetSelected(){
        model.restart();
        winner.set(null);
        cells.clear();
    }

    public void onClickedCellAt(int row,int col){
        Player playerThatMoved = model.mark(row,col);
        if(playerThatMoved != null){
            cells.put(""+row+col,playerThatMoved == null?null:playerThatMoved.toString());
            winner.set(model.getWiner() == null?null:model.getWiner().toString());
        }
    }
}
