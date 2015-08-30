package net.imthinker.android.customviewsample.presentation.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.imthinker.android.customviewsample.R;
import net.imthinker.android.customviewsample.databinding.FragmentMainBinding;
import net.imthinker.android.customviewsample.presentation.model.MainActivityFragmentViewModel;

import java.util.ArrayList;
import java.util.Random;

import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;

public class MainActivityFragment extends Fragment {
    private static final String TAG = MainActivityFragment.class.getSimpleName();

    private MainActivityFragmentViewModel mViewModel;
    private FragmentMainBinding mBinding;

    public static MainActivityFragment newInstance() {
        Log.d(TAG, "newInstance");
        MainActivityFragment fragment = new MainActivityFragment();
        Bundle args = fragment.getArguments();
        if (args == null) args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        initializeBinding();

        mViewModel.setLoading(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                if (random.nextInt() % 2 == 0) {
                    ArrayList<String> strings = new ArrayList<>(1000);
                    for (int i = 0; i < 1000; i++) {
                        strings.add(String.valueOf(i));
                    }
                    mViewModel.setStrings(strings);
                    mViewModel.setLoading(false);
                } else {
                    mViewModel.setError(true);
                }
            }
        }, 1000);
    }

    private void initializeBinding() {
        mBinding.mainProgressBar.setIndeterminateDrawable(
            new IndeterminateProgressDrawable(getActivity()));
        mViewModel = new MainActivityFragmentViewModel(this);
        mBinding.setModel(mViewModel);
    }

    public FragmentMainBinding getBinding() {
        return mBinding;
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }
}
