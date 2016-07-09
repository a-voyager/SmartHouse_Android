package top.wuhaojie.smarthouse.presenter.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import rx.Subscriber;
import top.wuhaojie.smarthouse.R;
import top.wuhaojie.smarthouse.base.interfaces.IView;
import top.wuhaojie.smarthouse.constant.Constants;
import top.wuhaojie.smarthouse.entities.MostValueBean;
import top.wuhaojie.smarthouse.entities.ResponseEntity;
import top.wuhaojie.smarthouse.injector.scrope.ContextLifeCycle;
import top.wuhaojie.smarthouse.interfaces.IMainView;
import top.wuhaojie.smarthouse.presenter.IPresenter;
import top.wuhaojie.smarthouse.utils.HttpHelper;

/**
 * Created by wuhaojie on 2016/7/7 11:28.
 */
public class MainPresenter implements IPresenter {

    private Context mContext;
    private IMainView mIMainView;
    private boolean isFirst = true;

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

    public void onRefresh() {
        HttpHelper.getInstance().getLastInfo(new Subscriber<ResponseEntity>() {
            @Override
            public void onCompleted() {
                mIMainView.stopRefresh();
            }

            @Override
            public void onError(Throwable e) {
                mIMainView.stopRefresh();
                mIMainView.showRefreshError();
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                if (responseEntity.isMIsError()) {
                    mIMainView.showRefreshError();
                    return;
                }
                ResponseEntity.MInfoItemsBean bean = responseEntity.getMInfoItems().get(0);
                mIMainView.setInfo(bean);
                alarmTmpHum(Integer.valueOf(bean.getMTemperature()));
            }
        });
        HttpHelper.getInstance().getMostValue("temperature", new Subscriber<MostValueBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(getClass().getSimpleName(), "错误！" + e.getMessage());
            }

            @Override
            public void onNext(MostValueBean mostValueBean) {
                mIMainView.setInfo(mostValueBean);
            }
        });
    }

    private void alarmTmpHum(int mTemperature) {
        if (mTemperature > Constants.MAX_TEMPPERATURE) {
            mIMainView.setTmpIcon2Red();
            if (isFirst) {
                mIMainView.showSnackBarAction("温度过高, 即将开启窗户", "撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHandler.removeMessages(OPEN_WINDOW);
                    }
                });
                mHandler.sendEmptyMessageDelayed(OPEN_WINDOW, 2000);
                isFirst = false;
            }
        } else if (mTemperature < Constants.MIN_TEMPPERATURE) {
            if (isFirst) {
                mIMainView.setTmpIcon2Blue();
                mIMainView.showSnackBarAction("温度过低, 即将关闭窗户", "撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHandler.removeMessages(CLOSE_WINDOW);
                    }
                });
                mHandler.sendEmptyMessageDelayed(CLOSE_WINDOW, 2000);
                isFirst = false;
            }
        } else {
            mIMainView.setTmpIcon2Blue();
        }
    }


    private static final int OPEN_WINDOW = 1000;
    private static final int CLOSE_WINDOW = 1001;

    private Handler mHandler = new MyHandler();

    public void switchCurtain(boolean currIsOpen) {
        if (currIsOpen)
            HttpHelper.getInstance().sendControlMessage(Constants.COMMAND_OPEN_WINDOW);
        else
            HttpHelper.getInstance().sendControlMessage(Constants.COMMAND_CLOSE_WINDOW);
    }

    public void switchFan(boolean currIsOpen) {
        if (currIsOpen)
            HttpHelper.getInstance().sendControlMessage(Constants.COMMAND_OPEN_FAN);
        else
            HttpHelper.getInstance().sendControlMessage(Constants.COMMAND_CLOSE_FAN);
    }

    public void switchAlarm(boolean currIsOpen) {
        if (currIsOpen)
            HttpHelper.getInstance().sendControlMessage(Constants.COMMAND_OPEN_ALARM);
        else
            HttpHelper.getInstance().sendControlMessage(Constants.COMMAND_CLOSE_ALARM);
    }

    public void TimerReFresh() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                onRefresh();
            }
        };
        new Timer().schedule(timerTask, 2000, 2000);
    }

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case OPEN_WINDOW:
                    HttpHelper.getInstance().sendControlMessage(Constants.COMMAND_OPEN_WINDOW);
                    break;
                case CLOSE_WINDOW:
                    HttpHelper.getInstance().sendControlMessage(Constants.COMMAND_CLOSE_WINDOW);
                    break;
            }
        }
    }

}
