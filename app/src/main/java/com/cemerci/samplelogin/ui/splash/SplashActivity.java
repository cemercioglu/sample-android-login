package com.cemerci.samplelogin.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.lifecycle.ViewModelProvider;

import com.cemerci.samplelogin.ui.login.LoginActivity;
import com.cemerci.samplelogin.ui.main.MainActivity;
import com.cemerci.samplelogin.R;
import com.cemerci.samplelogin.ViewModelProviderFactory;
import com.cemerci.samplelogin.databinding.ActivitySplashBinding;
import com.cemerci.samplelogin.ui.base.BaseActivity;

import javax.inject.Inject;

import com.cemerci.samplelogin.BR;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashListener {


    @Inject
    ViewModelProviderFactory factory;

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModelSplash;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = new ViewModelProvider(this, factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        mSplashViewModel.setListener(this);
        if (isNetworkConnected())
            mSplashViewModel.checkLoginStatus();
        else
            showToast(getResources().getString(R.string.check_network_connection));


        hideKeyboard();
    }

}
