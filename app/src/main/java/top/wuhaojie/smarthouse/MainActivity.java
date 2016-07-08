package top.wuhaojie.smarthouse;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import top.wuhaojie.smarthouse.base.BaseActivity;
import top.wuhaojie.smarthouse.base.BaseApplication;
import top.wuhaojie.smarthouse.entities.MostValueBean;
import top.wuhaojie.smarthouse.entities.ResponseEntity;
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
    @BindView(R.id.iv_tmp)
    ImageView mIvTmp;
    @BindView(R.id.tv_max_tmp)
    TextView mTvMaxTmp;
    @BindView(R.id.tv_min_tmp)
    TextView mTvMinTmp;
    @BindView(R.id.tv_out_door_tmp)
    TextView mTvOutDoorTmp;
    @BindView(R.id.tv_advice)
    TextView mTvAdvice;
    @BindView(R.id.tv_curr_tmp)
    TextView mTvCurrTmp;
    @BindView(R.id.sfl_main)
    SwipeRefreshLayout mSflMain;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.tv_curr_humi)
    TextView mTvCurrHumi;

    @BindView(R.id.iv_curtain)
    ImageView mIvCurtain;
    @BindView(R.id.iv_smoke)
    ImageView mIvSmoke;
    @BindView(R.id.tv_curr_smoke)
    TextView mTvCurrSmoke;


    @OnClick(R.id.cv_curtain)
    void onCurtainClick() {

    }

    @BindView(R.id.iv_fan)
    ImageView mIvFan;

    @OnClick(R.id.cv_fan)
    void onFanClick() {
    }

    @BindView(R.id.iv_alarm)
    ImageView mIvAlarm;

    @OnClick(R.id.cv_alarm)
    void onAlarmClick() {
    }


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
        mSflMain.setColorSchemeResources(R.color.blue01, R.color.green01, R.color.red01, R.color.orange01);
        mSflMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMainPresenter.onRefresh();
            }
        });
        // 程序启动后需要刷新状态
//        mSflMain.setRefreshing(true);
//        mMainPresenter.onRefresh();
    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClMain, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClMain, msg);
    }

    @Override
    public void stopRefresh() {
        mSflMain.setRefreshing(false);
    }

    @Override
    public void showRefreshError() {
        showSnackBarMsg("刷新失败，服务器睡觉了~");
    }

    @Override
    public void setInfo(ResponseEntity.MInfoItemsBean itemsBean) {
        mTvCurrTmp.setText(itemsBean.getMTemperature() + "℃");
        mTvCurrHumi.setText(itemsBean.getMHumidity() + "%");
        mTvCurrSmoke.setText(itemsBean.getMSmoke() + "");
    }

    @Override
    public void setInfo(MostValueBean mostValueBean) {
        mTvMaxTmp.setText("" + mostValueBean.getMax());
        mTvMinTmp.setText("" + mostValueBean.getMin());
    }

    @Override
    public void showSnackBarAction(String msg, String action, View.OnClickListener onClickListener) {
        SnackBarUtils.showAction(mClMain, msg, action, onClickListener);
    }

    @Override
    public void setTmpIcon2Red() {
        mIvTmp.setImageResource(R.drawable.ic_temp_red);
        mTvCurrTmp.setTextColor(ContextCompat.getColor(this, R.color.red08));
    }

    @Override
    public void setTmpIcon2Blue() {
        mIvTmp.setImageResource(R.drawable.ic_temp_blue);
        mTvCurrTmp.setTextColor(ContextCompat.getColor(this, R.color.dark_gray));
    }

}
