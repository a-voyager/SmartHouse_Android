package top.wuhaojie.smarthouse.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.wuhaojie.smarthouse.api.ApiService;
import top.wuhaojie.smarthouse.entities.MessageEntity;
import top.wuhaojie.smarthouse.entities.MostValueBean;
import top.wuhaojie.smarthouse.entities.ResponseEntity;

/**
 * Created by wuhaojie on 2016/7/7 14:19.
 */
public class HttpHelper {

    private Retrofit mRetrofit;

    private static final String BASE_URL = "http://192.168.1.185:8080/";

    private static final int DEFAULT_TIMEOUT = 10;
    private final ApiService mApiService;


    private HttpHelper() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiService = mRetrofit.create(ApiService.class);

    }

    private static class SingletonHolder {
        private static final HttpHelper INSTANCE = new HttpHelper();
    }

    public static HttpHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void getLastInfo(Subscriber<ResponseEntity> subscriber) {
        mApiService.getLastInfo()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void getMostValue(String type, Subscriber<MostValueBean> subscriber) {
        mApiService.getMostValue(type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void sendControlMessage(String message) {
        mApiService.sendMessage(new MessageEntity(0, message))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
    }

}
