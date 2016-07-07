package top.wuhaojie.smarthouse.interfaces;

import android.support.annotation.StringRes;

import top.wuhaojie.smarthouse.base.interfaces.IView;

/**
 * Created by wuhaojie on 2016/7/7 11:29.
 */
public interface IMainView extends IView {
    void showSnackBarMsg(@StringRes int msg);
}
