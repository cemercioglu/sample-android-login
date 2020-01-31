package com.cemerci.samplelogin.data.firebase;

import com.cemerci.samplelogin.data.model.User;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-29.
 */
public interface FirebaseHelper {
    void addNewUserToFb(User user, AddUserListener addUserListener);

    void getUser(String email, GetUserListener getUserListener);

    void updateUserFb(User user, UpdateUserListener updateUserListener);


    interface GetUserListener {
        void onComplete(User user);

        void onNotFound();
    }

    interface AddUserListener {
        void onComplete();

        void onUserAlreadyExists();
    }

    interface UpdateUserListener {
        void onUpdateComplete();

        void onUpdateError();
    }
}
