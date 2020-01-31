package com.cemerci.samplelogin.ui.register;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.lifecycle.ViewModelProvider;

import com.cemerci.samplelogin.BR;
import com.cemerci.samplelogin.R;
import com.cemerci.samplelogin.ViewModelProviderFactory;
import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.databinding.ActivityRegisterBinding;
import com.cemerci.samplelogin.ui.base.BaseActivity;
import com.cemerci.samplelogin.utils.ImageUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> implements RegisterListener {

    private final int SELECT_IMAGE_REQ = 11;
    String encodedImage;
    RegisterViewModel mRegisterViewModel;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;


    public static Intent newIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModelRegister;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mRegisterViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(RegisterViewModel.class);
        return mRegisterViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegisterViewModel.setListener(this);
        hideKeyboard();
    }

    @Override
    public void takePhoto() {

        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, SELECT_IMAGE_REQ);
    }

    @Override
    public void register() {
        ActivityRegisterBinding registerBinding = getViewDataBinding();
        registerBinding.tilEmail.setError(null);
        registerBinding.tilName.setError(null);
        registerBinding.tilLastName.setError(null);
        String email = registerBinding.tilEmail.getEditText().getText().toString();
        String name = registerBinding.tilName.getEditText().getText().toString();
        String lastName = registerBinding.tilLastName.getEditText().getText().toString();

        if (!mRegisterViewModel.isEmailValid(email)) {
            registerBinding.tilEmail.setError(getResources().getString(R.string.invalid_email));
            registerBinding.tilEmail.requestFocus();
        } else if (name.length() < 2) {
            registerBinding.tilName.setError(getResources().getString(R.string.invalid_name));
            registerBinding.tilName.requestFocus();
        } else if (lastName.length() < 2) {
            registerBinding.tilLastName.setError(getResources().getString(R.string.invalid_lastname));
            registerBinding.tilLastName.requestFocus();
        } else {
            getViewModel().register(new User(null, email, name, lastName, encodedImage,false));
        }
    }

    @Override
    public void registerSuccess() {
        showToast(getResources().getString(R.string.register_succes));
        finish();
    }

    @Override
    public void registerUserExists() {
        showToast(getResources().getString(R.string.user_exists));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        inputStreamImg = null;
        if (requestCode == SELECT_IMAGE_REQ) {
            Uri selectedPhotoUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedPhotoUri);

                encodedImage = ImageUtils.encodeImage(bitmap);

                getViewDataBinding().imgPp.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
