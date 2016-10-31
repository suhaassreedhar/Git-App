package com.suhaas.gitapp;


import com.suhaas.gitapp.entity.AuthenticatedUserBean;
import com.suhaas.gitapp.entity.BaseNetMethod;
import com.suhaas.gitapp.entity.UserBean;
import com.suhaas.gitapp.interfaces.UserService;
import com.suhaas.gitapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserMethod extends BaseNetMethod {
    private Retrofit retrofit;
    private UserService service;

    private static class Nested {
        static UserMethod instance = new UserMethod();
    }

    private UserMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);

        service = retrofit.create(UserService.class);
    }

    public static UserMethod getInstance() {
        return Nested.instance;
    }

    public void getAuthenticatedUser(Observer<AuthenticatedUserBean> observer, String auth) {
        service.getAuthenticatedUser(auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getFollowers(Observer<List<UserBean>> observer, String auth, int pageId) {
        service.getFollowers(auth, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUserFollowers(Observer<List<UserBean>> observer, String auth, String username, int pageId) {
        service.getUserFollowers(auth, username, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getFollowing(Observer<List<UserBean>> observer, String auth, int pageId) {
        service.getFollowing(auth, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUserFollowing(Observer<List<UserBean>> observer, String auth, String username, int pageId) {
        service.getUserFollowing(auth, username, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUser(Observer<UserBean> observer, String username) {
        service.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void isUserFollowed(Observer<String> observer, String auth, String username) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        UserService service = retrofit.create(UserService.class);
        service.isUserFollowed(auth, username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void followAUser(Observer<String> observer, String auth, String username) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        UserService service = retrofit.create(UserService.class);
        service.followAUser(auth, username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void unfollowAUser(Observer<String> observer, String auth, String username) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        UserService service = retrofit.create(UserService.class);
        service.unfollowAUser(auth, username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
