package com.suhaas.gitapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.suhaas.gitapp.entity.AppAuthorizationBean;
import com.suhaas.gitapp.entity.AuthenticatedUserBean;
import com.suhaas.gitapp.utils.AuthUtil;
import com.suhaas.gitapp.utils.SPUtil;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginContract.Presenter mPresenter;
    private LinearLayout mLoginLayout;
    private AppCompatButton mLoginBTN;
    private TextInputEditText mUsernameET;
    private TextInputEditText mPasswordET;
    private TextInputLayout mUsernameLayout;
    private TextInputLayout mPasswordLayout;

    private String username;
    private String password;

    private MaterialDialog loadingDialog;

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    public void initViews() {
        if (!SPUtil.getBoolean(this, Constants.LOCAL_CONFIGURATION, Constants.FIRST_USED, true)) {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        new LoginPresenter(this, this);
        mPresenter.start();

        mLoginLayout = (LinearLayout) findViewById(R.id.login_layout);
        mLoginBTN = (AppCompatButton) findViewById(R.id.login_sign_in_btn);
        mUsernameET = (TextInputEditText) findViewById(R.id.login_username_et);
        mPasswordET = (TextInputEditText) findViewById(R.id.login_password_et);
        mUsernameLayout = (TextInputLayout) findViewById(R.id.login_username_layout);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.login_password_layout);

        mUsernameLayout.setHint(getString(R.string.username));
        mPasswordLayout.setHint(getString(R.string.password));
        mLoginBTN.setText(R.string.login);
        mUsernameLayout.setErrorEnabled(true);
        mUsernameLayout.setErrorEnabled(true);

        setupButtons();
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_login);
    }

    private void setupButtons() {
        mLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsernameET.getText().toString();
                password = mPasswordET.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    mUsernameLayout.setError(getString(R.string.please_input_username));
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPasswordLayout.setError(getString(R.string.please_input_password));
                    return;
                }
                if (mPresenter != null) {
                    loadingDialog = new MaterialDialog.Builder(LoginActivity.this)
                            .progress(true, 0)
                            .cancelable(false)
                            .title(R.string.please_wait)
                            .content(R.string.loading)
                            .build();
                    loadingDialog.show();
                    mPresenter.login();
                }
            }
        });
    }

    private void loadUser() {
        mPresenter.loadUserInfo();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loginSuccess() {
        loadUser();
    }

    @Override
    public void loginFail() {
        loadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), "login fail", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void logining(AppAuthorizationBean bean) {
        String username = mUsernameET.getText().toString();
        String password = mPasswordET.getText().toString();
        String auth = AuthUtil.generateAuth(username, password);
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_AUTH, auth);
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_USERNAME, username);
    }

    @Override
    public void loadUserInfo(AuthenticatedUserBean user) {
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_EMAIL, user.getEmail());
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_AVATAR, user.getAvatar_url());
        SPUtil.putString(this, Constants.USER_INFO, Constants.USER_LOGIN, user.getLogin());
    }

    @Override
    public void loadSuccess() {
        loadingDialog.dismiss();
        SPUtil.putBoolean(this, Constants.LOCAL_CONFIGURATION, Constants.FIRST_USED, false);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loadFail() {
        loadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), "login fail", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public String getAuth() {
        String username = mUsernameET.getText().toString();
        String password = mPasswordET.getText().toString();
        String auth = AuthUtil.generateAuth(username, password);
        return auth;
    }
}
