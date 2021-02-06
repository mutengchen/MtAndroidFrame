package com.cmt.mt_android_frame.common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    private String TAG = BaseFragment.class.getSimpleName();
    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;
    private View rootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            rootView = view;
            if(getUserVisibleHint()){
                if(isFirstVisible){
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView?rootView:view, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(rootView == null)
            return;
        if(isFirstVisible && isVisibleToUser){
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if(isVisibleToUser){
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if(isFragmentVisible){
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    protected  void reuseView(boolean isReuse){
        isReuseView = isReuse;
    }
    private void initVariable(){
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public abstract void loadData();
    protected void onFragmentVisibleChange(boolean isVisible){

    }
    protected void onFragmentFirstVisible(){

    }
    protected boolean isFragmentVisible(){
        return isFragmentVisible;
    }
}
