package top.wuhaojie.smarthouse.presenter;

import android.os.Bundle;

import top.wuhaojie.smarthouse.base.interfaces.IView;

/**
 * Created by wuhaojie on 2016/7/7 11:32.
 */
public interface IPresenter {
    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onStart();

    void onPause();

    void onStop();

    void onDestroy();

    void attachView(IView v);
}
