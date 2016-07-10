package top.wuhaojie.smarthouse;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import top.wuhaojie.smarthouse.base.BaseActivity;
import top.wuhaojie.smarthouse.base.BaseApplication;
import top.wuhaojie.smarthouse.entities.MostValueBean;
import top.wuhaojie.smarthouse.entities.ResponseEntity;
import top.wuhaojie.smarthouse.injector.component.ActivityComponent;
import top.wuhaojie.smarthouse.injector.component.DaggerActivityComponent;
import top.wuhaojie.smarthouse.injector.module.ActivityModule;
import top.wuhaojie.smarthouse.interfaces.IMainView;
import top.wuhaojie.smarthouse.presenter.impl.MainPresenter;
import top.wuhaojie.smarthouse.ui.SwitchView;
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
    @BindView(R.id.tv_curr_smoke)
    TextView mTvCurrSmoke;
    @BindView(R.id.tv_curr_humi)
    TextView mTvCurrHumi;

    @BindView(R.id.sv_curtain)
    SwitchView mSvCurtain;
    @BindView(R.id.sv_fan)
    SwitchView mSvFan;
    @BindView(R.id.sv_alarm)
    SwitchView mSvAlarm;


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
        mSflMain.setColorSchemeResources(R.color.orange01, R.color.green01, R.color.red01, R.color.blue01);
        mSflMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMainPresenter.onRefresh();
            }
        });
        // 程序启动后需要刷新状态
        mSflMain.setRefreshing(true);
        mMainPresenter.onRefresh();
        // 定时刷新状态
        mMainPresenter.TimerReFresh();


        mSvCurtain.setOnSwitchListener(new SwitchView.OnSwitchListener() {
            @Override
            public void onSwitch(boolean currIsOpen) {
                mMainPresenter.switchCurtain(currIsOpen);
            }
        });

        mSvFan.setOnSwitchListener(new SwitchView.OnSwitchListener() {
            @Override
            public void onSwitch(boolean currIsOpen) {
                mMainPresenter.switchFan(currIsOpen);
            }
        });

        mSvAlarm.setOnSwitchListener(new SwitchView.OnSwitchListener() {
            @Override
            public void onSwitch(boolean currIsOpen) {
                mMainPresenter.switchAlarm(currIsOpen);
            }
        });

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
//        mSvCurtain.setOpen(Boolean.valueOf(itemsBean.getMCurtainState()));
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
