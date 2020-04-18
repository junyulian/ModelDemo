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
import com.jun.mvvmdemo.databinding.ActivityMvcBinding;
import com.jun.mvvmdemo.model.Board;
import com.jun.mvvmdemo.model.Player;

public class MvcActivity extends AppCompatActivity {

    private static String TAG = MvcActivity.class.getSimpleName();

    private ActivityMvcBinding binding;
    private Board model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvc);
        model = new Board();
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
                model.restart();
                resetView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetView() {
        binding.tvWinnerLabel.setText("");
        binding.llWinder.setVisibility(View.GONE);

        for(int i=0; i<binding.btnGrid.getChildCount(); i++){
            ((Button)binding.btnGrid.getChildAt(i)).setText("");
        }
    }

    public void onCellClicked(View v){
        Button button = (Button)v;

        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.e(TAG,"click Row:["+row+","+col+"]");

        Player playerThatMoved = model.mark(row,col);
        if(playerThatMoved != null){
            button.setText(playerThatMoved.toString());
            if(model.getWiner() != null){
                binding.tvWinnerLabel.setText(playerThatMoved.toString());
                binding.llWinder.setVisibility(View.VISIBLE);
            }
        }

    }
}
