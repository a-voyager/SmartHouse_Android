package top.wuhaojie.smarthouse.injector.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import top.wuhaojie.smarthouse.injector.scrope.ActivityScope;
import top.wuhaojie.smarthouse.injector.scrope.ContextLifeCycle;

/**
 * Created by wuhaojie on 2016/7/7 10:53.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity getActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    @ContextLifeCycle("Activity")
    public Context getContext() {
        return mActivity;
    }
}
