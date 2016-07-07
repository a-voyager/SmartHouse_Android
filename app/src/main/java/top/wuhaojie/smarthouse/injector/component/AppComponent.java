package top.wuhaojie.smarthouse.injector.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import top.wuhaojie.smarthouse.base.BaseApplication;
import top.wuhaojie.smarthouse.injector.module.AppModule;
import top.wuhaojie.smarthouse.injector.scrope.ContextLifeCycle;

/**
 * Created by wuhaojie on 2016/7/7 10:32.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    BaseApplication app();

    @ContextLifeCycle("App")
    Context context();

}
