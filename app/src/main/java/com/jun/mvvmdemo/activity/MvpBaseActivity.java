package com.jun.mvvmdemo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jun.mvvmdemo.presenter.BasePresenter;
import com.jun.mvvmdemo.viewlayer.BaseView;

public abstract class MvpBaseActivity<V extends BaseView,P extends BasePresenter<V>> extends AppCompatActivity {

    private P presenter;
    private V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.presenter == null ){
            this.presenter = createPresenter();
        }
        if(this.view == null){
            this.view = createView();
        }
        if(this.presenter != null && this.view != null){
            this.presenter.attachView(view);
        }


    }

    public P getPresenter(){
        return presenter;
    }
    public abstract P createPresenter();
    public abstract V createView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.presenter != null && this.view != null){
            this.presenter.detachView();
        }
    }
}
