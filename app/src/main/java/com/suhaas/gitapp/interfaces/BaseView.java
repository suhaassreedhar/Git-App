package com.suhaas.gitapp.interfaces;


public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}