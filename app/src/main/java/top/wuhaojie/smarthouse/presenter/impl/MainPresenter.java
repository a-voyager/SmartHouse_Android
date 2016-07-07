package top.wuhaojie.smarthouse.presenter.impl;

import android.content.Context;
import android.os.Bundle;

import javax.inject.Inject;

import top.wuhaojie.smarthouse.R;
import top.wuhaojie.smarthouse.base.interfaces.IView;
import top.wuhaojie.smarthouse.injector.scrope.ContextLifeCycle;
import top.wuhaojie.smarthouse.interfaces.IMainView;
import top.wuhaojie.smarthouse.presenter.IPresenter;

/**
 * Created by wuhaojie on 2016/7/7 11:28.
 */
public class MainPresenter implements IPresenter {

    private Context mContext;
    private IMainView mIMainView;

    @Inject
    public MainPresenter(@ContextLifeCycle("Activity") Context context) {
        mContext = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void attachView(IView v) {
        mIMainView = (IMainView) v;
    }

    public void sendMsg() {
        mIMainView.showSnackBarMsg(R.string.app_name);
    }
}
