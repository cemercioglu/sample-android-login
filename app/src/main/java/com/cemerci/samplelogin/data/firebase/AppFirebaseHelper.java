package com.cemerci.samplelogin.data.firebase;

import androidx.annotation.NonNull;

import com.cemerci.samplelogin.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-29.
 */
public class AppFirebaseHelper implements FirebaseHelper {
    private DatabaseReference mDatabaseReference;

    @Inject
    AppFirebaseHelper() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReference(User.class.getSimpleName());
    }

    @Override
    public void addNewUserToFb(final User user, final AddUserListener addUserListener) {

        getUser(user.getEmail(), new GetUserListener() {
            @Override
            public void onComplete(User user) {
                addUserListener.onUserAlreadyExists();
            }

            @Override
            public void onNotFound() {
                String id = mDatabaseReference.push().getKey();
                user.setId(id);
                mDatabaseReference.child(id).setValue(user);
//                uploadImage(uriFile, id);
                addUserListener.onComplete();
            }
        });

    }

    @Override
    public void getUser(final String email, final GetUserListener getUserListener) {
        Query query = mDatabaseReference.child("").orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() == 0) {
                            getUserListener.onNotFound();
                            return;
                        }
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            User user = snapshot.getValue(User.class);
                            assert user != null;
                            if (!user.getRemoveStatus()) {
                                getUserListener.onComplete(user);
                                break;

                            } else {
                                getUserListener.onNotFound();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void updateUserFb(User user, final UpdateUserListener updateUserListener) {
        mDatabaseReference.child(user.getId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateUserListener.onUpdateComplete();

            }
        });

    }


    //// TODO: 2020-01-31 will add fbStorage
//    private void uploadImage(Uri uriFile, String userId) {
//        StorageReference riversRef = mStorageReference.child("images/" + userId + ".jpg");
//
//        riversRef.putFile(uriFile)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // Get a URL to the uploaded content
////                        Uri downloadUrl = taskSnapshot.get();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle unsuccessful uploads
//                        // ...
//                    }
//                });
//
//    }
}
