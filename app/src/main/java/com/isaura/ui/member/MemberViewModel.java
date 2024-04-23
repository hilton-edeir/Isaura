package com.isaura.ui.member;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MemberViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MemberViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Member fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}