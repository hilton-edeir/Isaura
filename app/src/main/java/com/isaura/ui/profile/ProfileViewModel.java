package com.isaura.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileViewModel extends ViewModel {

    private FirebaseAuth mAuth;
    private final MutableLiveData<String> mText;

    public ProfileViewModel() {
        mAuth = FirebaseAuth.getInstance();
        mText = new MutableLiveData<>();
        mText.setValue(mAuth.getCurrentUser().getDisplayName());
    }

    public LiveData<String> getText() {
        return mText;
    }
}