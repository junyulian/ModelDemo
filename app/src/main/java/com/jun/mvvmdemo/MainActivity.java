package com.jun.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jun.mvvmdemo.activity.FirstActivity;
import com.jun.mvvmdemo.activity.MvcActivity;
import com.jun.mvvmdemo.activity.MvpActivity;
import com.jun.mvvmdemo.activity.MvvmActivity;
import com.jun.mvvmdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.btnFirst.setOnClickListener(this);
        binding.btnMvc.setOnClickListener(this);
        binding.btnMvp.setOnClickListener(this);
        binding.btnMvvm.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_first:
                goToTargetPage(FirstActivity.class);
                break;
            case R.id.btn_mvc:
                goToTargetPage(MvcActivity.class);
                break;
            case R.id.btn_mvp:
                goToTargetPage(MvpActivity.class);
                break;
            case R.id.btn_mvvm:
                goToTargetPage(MvvmActivity.class);
                break;
        }
    }


    private <T> void goToTargetPage(Class<T> clazz) {
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }
}
