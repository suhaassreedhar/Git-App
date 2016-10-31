package com.suhaas.gitapp;

import android.content.Context;

import com.suhaas.gitapp.entity.AppAuthorizationBean;
import com.suhaas.gitapp.entity.AuthenticatedUserBean;
import com.suhaas.gitapp.entity.AuthorizationRequest;
import com.suhaas.gitapp.entity.NetPresenter;
import com.suhaas.gitapp.utils.SPUtil;

import java.util.ArrayList;

import rx.Subscriber;

public class LoginPresenter extends NetPresenter implements LoginContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private LoginContract.View mLoginView;

    private Subscriber<AppAuthorizationBean> mLoginSubscriber;
    private Subscriber<AuthenticatedUserBean> mUserSubscriber;

    private OverviewMethod overviewMethod;
    private UserMethod userMethod;

    public LoginPresenter(Context context, LoginContract.View view) {
        mContext = context;
        mLoginView = view;
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {
        overviewMethod = getMethod(OverviewMethod.class);
        userMethod = getMethod(UserMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(mLoginSubscriber, mUserSubscriber);
    }

    @Override
    public void login() {
        mLoginSubscriber = new Subscriber<AppAuthorizationBean>() {
            @Override
            public void onCompleted() {
                mLoginView.loginSuccess();
            }

            @Override
            public void onError(Throwable e) {
                mLoginView.loginFail();
                e.printStackTrace();
            }

            @Override
            public void onNext(AppAuthorizationBean appAuthorization) {
                mLoginView.logining(appAuthorization);
            }
        };
        String auth = mLoginView.getAuth();
        AuthorizationRequest request = new AuthorizationRequest();
        request.setClient_secret(Api.CLIENT_SERECT);
        ArrayList<String> scopes = new ArrayList<>();
        for (String str : Api.SCPOES) {
            scopes.add(str);
        }
        request.setScopes(scopes);
        request.setNote(Api.NOTE);
        overviewMethod.getOrCreateAuthorization(mLoginSubscriber, auth, request);
    }

    @Override
    public void loadUserInfo() {
        mUserSubscriber = new Subscriber<AuthenticatedUserBean>() {
            @Override
            public void onCompleted() {
                mLoginView.loadSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mLoginView.loadFail();
            }

            @Override
            public void onNext(AuthenticatedUserBean authenticatedUser) {
                mLoginView.loadUserInfo(authenticatedUser);
            }
        };
        userMethod.getAuthenticatedUser(mUserSubscriber, SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null));
    }
}
