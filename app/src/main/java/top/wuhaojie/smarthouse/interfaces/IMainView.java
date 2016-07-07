package top.wuhaojie.smarthouse.interfaces;

import android.support.annotation.StringRes;

import top.wuhaojie.smarthouse.base.interfaces.IView;
import top.wuhaojie.smarthouse.entities.MostValueBean;
import top.wuhaojie.smarthouse.entities.ResponseEntity;

/**
 * Created by wuhaojie on 2016/7/7 11:29.
 */
public interface IMainView extends IView {
    void showSnackBarMsg(@StringRes int msg);

    void showSnackBarMsg(String msg);

    void stopRefresh();

    void showRefreshError();

    void setInfo(ResponseEntity.MInfoItemsBean responseEntity);

    void setInfo(MostValueBean mostValueBean);
}
