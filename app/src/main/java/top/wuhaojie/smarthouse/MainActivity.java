package top.wuhaojie.smarthouse;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;

import javax.inject.Inject;

import butterknife.BindView;
import top.wuhaojie.smarthouse.base.BaseActivity;
import top.wuhaojie.smarthouse.base.BaseApplication;
import top.wuhaojie.smarthouse.injector.component.ActivityComponent;
import top.wuhaojie.smarthouse.injector.component.DaggerActivityComponent;
import top.wuhaojie.smarthouse.injector.module.ActivityModule;
import top.wuhaojie.smarthouse.interfaces.IMainView;
import top.wuhaojie.smarthouse.presenter.impl.MainPresenter;
import top.wuhaojie.smarthouse.utils.SnackBarUtils;

public class MainActivity extends BaseActivity implements IMainView {


    @Inject
    MainPresenter mMainPresenter;
    @BindView(R.id.cl_main)
    CoordinatorLayout mClMain;


    @Override
    protected void initializePresenter() {
        mMainPresenter.attachView(this);
    }

    @Override
    public void initializeInjector() {
        ActivityComponent activityComponent = DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule(this))
                .appComponent(((BaseApplication) getApplication()).getAppComponent())
                .build();
        activityComponent.inject(this);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClMain, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClMain, msg);
    }

}
