package top.wuhaojie.smarthouse.base.interfaces;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

/**
 * Created by wuhaojie on 2016/7/7 10:44.
 */
public interface IView {
    @LayoutRes
    int getLayoutResID();

    void initViews(Bundle savedInstanceState);
}
