package net.imthinker.android.customviewsample.presentation.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import net.imthinker.android.customviewsample.BR;
import net.imthinker.android.customviewsample.databinding.FragmentMainBinding;
import net.imthinker.android.customviewsample.presentation.fragment.MainActivityFragment;

import java.util.List;

public class MainActivityFragmentViewModel extends BaseObservable {
    private boolean mLoading;
    private boolean mError;
    private List<String> mStrings;
    private MainActivityFragment mFragment;
    private FragmentMainBinding mBinding;

    public MainActivityFragmentViewModel(MainActivityFragment fragment) {
        mFragment = fragment;
        mBinding = fragment.getBinding();
    }

    @Bindable
    public boolean isLoading() {
        return mLoading;
    }

    public void setLoading(boolean loading) {
        mLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public boolean isError() {
        return mError;
    }

    public void setError(boolean error) {
        mError = error;
        setLoading(false);
    }

    public List<String> getStrings() {
        return mStrings;
    }

    public void setStrings(List<String> strings) {
        mStrings = strings;
        mBinding.mainMyListView.updateAll(strings);
        setError(false);
    }
}
