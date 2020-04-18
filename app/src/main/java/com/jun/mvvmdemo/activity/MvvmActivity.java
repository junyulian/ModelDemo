package com.jun.mvvmdemo.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jun.mvvmdemo.R;
import com.jun.mvvmdemo.databinding.ActivityMvvmBinding;
import com.jun.mvvmdemo.viewmodel.TicTacToeViewModel;

/**
 * Presenter改成ViewModel, 成MVVM
 * databinding的引入才有了MVVM
 */
public class MvvmActivity extends AppCompatActivity {

    private ActivityMvvmBinding binding;
    TicTacToeViewModel viewModel = new TicTacToeViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        binding.setViewModel(viewModel);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactoe,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_reset:
                viewModel.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
