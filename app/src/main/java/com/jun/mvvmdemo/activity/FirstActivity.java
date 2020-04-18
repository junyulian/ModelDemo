package com.jun.mvvmdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jun.mvvmdemo.R;
import com.jun.mvvmdemo.databinding.ActivityFirstBinding;



import static com.jun.mvvmdemo.activity.FirstActivity.Player.O;
import static com.jun.mvvmdemo.activity.FirstActivity.Player.X;

public class FirstActivity extends AppCompatActivity {


    private static String TAG = FirstActivity.class.getSimpleName();
    private ActivityFirstBinding binding;

    public enum Player{X,O}

    public class Cell{
        private Player value;
        public Player getValue() {
            return value;
        }
        public void setValue(Player value) {
            this.value = value;
        }
    }

    private Cell[][] cells = new Cell[3][3];

    private enum GameState {IN_PROGRESS,FINISHED}

    private Player winner;
    private GameState state;
    private Player currentTurn;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first);
        restart();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tictactoe,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_reset:
                restart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onCellClicked(View v){
        Button button = (Button)v;

        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.e(TAG,"click Row:["+row+","+col+"]");

        Player playerThatMoved = mark(row,col);
        if(playerThatMoved != null){
            button.setText(playerThatMoved.toString());
            if(getWiner() != null){
                binding.tvWinnerLabel.setText(playerThatMoved.toString());
                binding.llWinder.setVisibility(View.VISIBLE);
            }
        }

    }

    private void restart() {
        clearCells();
        winner = null;
        currentTurn = X;
        state = GameState.IN_PROGRESS;

        binding.tvWinnerLabel.setText("");
        binding.llWinder.setVisibility(View.GONE);

        for(int i=0; i<binding.btnGrid.getChildCount(); i++){
            ((Button)binding.btnGrid.getChildAt(i)).setText("");
        }
    }

    private Player mark(int row, int col) {
        
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

    private Player getWiner() {

        return winner;
    }

    private void clearCells() {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                cells[i][j] = new Cell();
            }
        }
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









    
}
