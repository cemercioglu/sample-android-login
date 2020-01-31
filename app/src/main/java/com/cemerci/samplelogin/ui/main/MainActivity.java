package com.cemerci.samplelogin.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.cemerci.samplelogin.BR;
import com.cemerci.samplelogin.R;
import com.cemerci.samplelogin.ViewModelProviderFactory;
import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.databinding.ActivityMainBinding;
import com.cemerci.samplelogin.ui.base.BaseActivity;
import com.cemerci.samplelogin.ui.splash.SplashActivity;
import com.cemerci.samplelogin.utils.ImageUtils;

import java.io.IOException;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainListener {
    private final int SELECT_IMAGE_REQ = 11;

    MainViewModel mMainViewModel;
    @Inject
    ViewModelProviderFactory factory;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModelMain;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel.setListener(this);
        hideKeyboard();
        getViewModel().fillUserInfo();
    }

    @Override
    public void loggedOut() {
        startActivity(SplashActivity.newIntent(this));
        finish();
    }

    @Override
    public void updateUser(String userId, boolean isRemoved) {
        String email = getViewDataBinding().tilEmail.getEditText().getText().toString();
        String name = getViewDataBinding().tilName.getEditText().getText().toString();
        String lastName = getViewDataBinding().tilLastName.getEditText().getText().toString();

        User user = new User(userId, email, name, lastName, null, isRemoved);
        getViewModel().updateUserFb(user);
    }

    @Override
    public void takePhoto() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, SELECT_IMAGE_REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        inputStreamImg = null;
        if (requestCode == SELECT_IMAGE_REQ) {
            Uri selectedPhotoUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedPhotoUri);

                getViewModel().imageBase64.set(ImageUtils.encodeImage(bitmap));

                getViewDataBinding().imgPp.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
