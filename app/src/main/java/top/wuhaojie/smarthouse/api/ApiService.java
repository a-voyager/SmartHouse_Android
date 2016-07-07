package top.wuhaojie.smarthouse.api;

import retrofit2.http.GET;
import rx.Observable;
import top.wuhaojie.smarthouse.entities.ResponseEntity;

/**
 * Created by wuhaojie on 2016/7/7 14:02.
 */
public interface ApiService {

    @GET("last-info.action")
    Observable<ResponseEntity> getLastInfo();

}
