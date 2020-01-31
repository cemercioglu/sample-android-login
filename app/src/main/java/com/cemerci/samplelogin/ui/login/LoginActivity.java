package com.cemerci.samplelogin.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.cemerci.samplelogin.BR;
import com.cemerci.samplelogin.R;
import com.cemerci.samplelogin.ViewModelProviderFactory;
import com.cemerci.samplelogin.databinding.ActivityLoginBinding;
import com.cemerci.samplelogin.ui.base.BaseActivity;
import com.cemerci.samplelogin.ui.main.MainActivity;
import com.cemerci.samplelogin.ui.register.RegisterActivity;
import com.cemerci.samplelogin.utils.AppConstants;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginListener {
    private static String TAG = LoginActivity.class.getSimpleName();


    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    LoginViewModel mLoginViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModelLogin;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginViewModel.setListener(this);
        hideKeyboard();
    }

    @Override
    public void handleError(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
    }

    @Override
    public void openRegisterActivity() {
        Intent intent = RegisterActivity.newIntent(LoginActivity.this);
        startActivity(intent);
    }

    @Override
    public void login() {
        getViewDataBinding().tilEmail.setError(null);
        getViewDataBinding().tilPassword.setError(null);
        String email = getViewDataBinding().tilEmail.getEditText().getText().toString();
        String password = getViewDataBinding().tilPassword.getEditText().getText().toString();
        if (!mLoginViewModel.isEmailValid(email))
            getViewDataBinding().tilEmail.setError(getResources().getString(R.string.invalid_email));
            //for demo password disabled
//        else if (password.length() < AppConstants.MIN_PASSWORD_LENGTH)
//            getViewDataBinding().tilPassword.setError(getResources().getString(R.string.invalid_password));
        else
            mLoginViewModel.loginAction(email, password);
    }

    @Override
    public void userNotFound() {
        showToast(getResources().getString(R.string.user_not_found));
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }
}
