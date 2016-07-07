package top.wuhaojie.smarthouse.injector.component;

import android.app.Activity;
import android.content.Context;

import dagger.Component;
import top.wuhaojie.smarthouse.MainActivity;
import top.wuhaojie.smarthouse.injector.module.ActivityModule;
import top.wuhaojie.smarthouse.injector.scrope.ActivityScope;
import top.wuhaojie.smarthouse.injector.scrope.ContextLifeCycle;

/**
 * Created by wuhaojie on 2016/7/7 10:57.
 */
@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    Activity activity();

    @ContextLifeCycle("Activity")
    Context activityContext();

    @ContextLifeCycle("App")
    Context appContext();


}
