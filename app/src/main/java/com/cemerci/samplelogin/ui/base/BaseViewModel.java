package com.cemerci.samplelogin.ui.base;

import android.text.TextUtils;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.cemerci.samplelogin.data.DataManager;
import com.cemerci.samplelogin.utils.CommonUtils;
import com.cemerci.samplelogin.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-21.
 */
public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;

    private final ObservableBoolean mIsLoading = new ObservableBoolean();

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mListener;

    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public N getListener() {
        return mListener.get();
    }

    public void setListener(N listener) {
        this.mListener = new WeakReference<>(listener);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }


    public boolean isEmailValid(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        return CommonUtils.isEmailValid(email);
    }


}
