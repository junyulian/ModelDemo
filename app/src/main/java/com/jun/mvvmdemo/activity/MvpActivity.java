package com.jun.mvvmdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.jun.mvvmdemo.R;
import com.jun.mvvmdemo.databinding.ActivityMvpBinding;
import com.jun.mvvmdemo.presenter.TicTacToePresenter;
import com.jun.mvvmdemo.viewlayer.TicTacToeView;

public class MvpActivity extends MvpBaseActivity<TicTacToeView, TicTacToePresenter> {
    private final static String TAG = MvpActivity.class.getSimpleName();
    private ActivityMvpBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvp);


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
                getPresenter().onResetSelected();
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

        getPresenter().onButtonSelected(row,col);

    }

    @Override
    public TicTacToePresenter createPresenter() {
        return new TicTacToePresenter();
    }

    @Override
    public TicTacToeView createView() {
        return new TicTacToeView() {
            @Override
            public void showWinner(String winnerLabel) {
                binding.llWinder.setVisibility(View.VISIBLE);
                binding.tvWinnerLabel.setText(winnerLabel);
            }

            @Override
            public void clearWinnerDisplay() {
                binding.llWinder.setVisibility(View.GONE);
                binding.tvWinnerLabel.setText("");
            }

            @Override
            public void clearButtons() {
                for(int i=0; i<binding.btnGrid.getChildCount(); i++){
                    ((Button)binding.btnGrid.getChildAt(i)).setText("");
                }
            }

            @Override
            public void setButtonText(int row, int col, String text) {
                Button btn = (Button)binding.btnGrid.findViewWithTag(""+row+col);
                if(btn != null){
                    btn.setText(text);
                }
            }
        };
    }
}
