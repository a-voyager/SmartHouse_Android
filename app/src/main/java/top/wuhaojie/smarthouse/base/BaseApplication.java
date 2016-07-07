package top.wuhaojie.smarthouse.base;

import android.app.Application;

import top.wuhaojie.smarthouse.injector.component.AppComponent;
import top.wuhaojie.smarthouse.injector.component.DaggerAppComponent;
import top.wuhaojie.smarthouse.injector.interfaces.IConfigInjector;
import top.wuhaojie.smarthouse.injector.module.AppModule;

/**
 * Created by wuhaojie on 2016/7/7 10:28.
 */
public class BaseApplication extends Application implements IConfigInjector {


    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void initializeInjector() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
