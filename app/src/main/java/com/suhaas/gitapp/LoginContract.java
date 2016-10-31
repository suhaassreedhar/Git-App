package com.suhaas.gitapp;


import com.suhaas.gitapp.entity.AppAuthorizationBean;
import com.suhaas.gitapp.entity.AuthenticatedUserBean;
import com.suhaas.gitapp.interfaces.BasePresenter;
import com.suhaas.gitapp.interfaces.BaseView;

public class LoginContract {
    public interface Presenter extends BasePresenter {
        void login();

        void loadUserInfo();
    }

    public interface View extends BaseView<Presenter> {
        void loginSuccess();

        void loginFail();

        void logining(AppAuthorizationBean bean);

        void loadUserInfo(AuthenticatedUserBean user);

        void loadSuccess();

        void loadFail();

        String getAuth();
    }
}
