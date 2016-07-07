package top.wuhaojie.smarthouse.api;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import top.wuhaojie.smarthouse.entities.MostValueBean;
import top.wuhaojie.smarthouse.entities.ResponseEntity;

/**
 * Created by wuhaojie on 2016/7/7 14:02.
 */
public interface ApiService {

    @POST("last-info.action")
    Observable<ResponseEntity> getLastInfo();

    @GET("most-value.action")
    Observable<MostValueBean> getMostValue(@Query("type") String type);

}
