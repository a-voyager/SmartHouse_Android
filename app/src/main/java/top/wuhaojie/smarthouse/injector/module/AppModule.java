package top.wuhaojie.smarthouse.injector.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import top.wuhaojie.smarthouse.base.BaseApplication;
import top.wuhaojie.smarthouse.injector.scrope.ContextLifeCycle;

/**
 * Created by wuhaojie on 2016/7/7 10:33.
 */
@Module
public class AppModule {
    private BaseApplication mBaseApplication;
    private Context mContext;

    public AppModule(BaseApplication baseApplication) {
        mBaseApplication = baseApplication;
        mContext = mBaseApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    public BaseApplication getBaseApplication() {
        return mBaseApplication;
    }

    @Provides
    @Singleton
    @ContextLifeCycle("App")
    public Context getContext() {
        return mContext;
    }
}
