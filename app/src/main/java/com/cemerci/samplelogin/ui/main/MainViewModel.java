package com.cemerci.samplelogin.ui.main;

import androidx.databinding.ObservableField;

import com.cemerci.samplelogin.data.DataManager;
import com.cemerci.samplelogin.data.firebase.FirebaseHelper;
import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.ui.base.BaseViewModel;
import com.cemerci.samplelogin.utils.rx.SchedulerProvider;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-31.
 */
public class MainViewModel extends BaseViewModel<MainListener> {
    private final ObservableField<String> userName = new ObservableField<>();
    private final ObservableField<String> userLastName = new ObservableField<>();
    private final ObservableField<String> userEmail = new ObservableField<>();
    public final ObservableField<String> imageBase64 = new ObservableField<>();

    public ObservableField<String> getUserName() {
        return userName;
    }

    public ObservableField<String> getUserLastName() {
        return userLastName;
    }

    public ObservableField<String> getUserEmail() {
        return userEmail;
    }

    public ObservableField<String> getImageBase64() {
        return imageBase64;
    }


    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void fillUserInfo() {
        setIsLoading(true);
        getDataManager().getUser(getDataManager().getUserInfo().getEmail(), new FirebaseHelper.GetUserListener() {
            @Override
            public void onComplete(User user) {
                userName.set(user.getName());
                userLastName.set(user.getLastName());
                userEmail.set(user.getEmail());
                imageBase64.set(user.getUserPhoto());
                setIsLoading(false);

            }

            @Override
            public void onNotFound() {
                setIsLoading(false);
            }
        });


    }

    public void onPhotoClick() {
        getListener().takePhoto();
    }

    public void onSaveClick() {
        getListener().updateUser(getDataManager().getUserInfo().getId(), false);
    }

    public void onRemoveAccountClick() {
        getListener().updateUser(getDataManager().getUserInfo().getId(), true);
    }

    public void onLoggedOutClick() {
        getDataManager().loggedOut();
        getListener().loggedOut();
    }

    void updateUserFb(final User user) {
        setIsLoading(true);

        user.setUserPhoto(imageBase64.get());
        getDataManager().updateUserFb(user, new FirebaseHelper.UpdateUserListener() {
            @Override
            public void onUpdateComplete() {
                getDataManager().updateUserInfo(user, getDataManager().getUserMode());
                if (user.getRemoveStatus()) {
                    onLoggedOutClick();
                }
                setIsLoading(false);
            }

            @Override
            public void onUpdateError() {

            }
        });
    }

    void removeUser() {

    }
}
