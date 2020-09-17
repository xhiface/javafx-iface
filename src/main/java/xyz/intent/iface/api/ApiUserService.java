package xyz.intent.iface.api;

import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import xyz.intent.iface.model.ApiUserDto;
import xyz.intent.iface.model.ApiUserLoginVo;
import xyz.intent.iface.model.ApiUserVo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * xyz.zzyitj.iface.api
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/9/14 10:31
 * @since 1.0
 */
public class ApiUserService {
    private static final String TAG = ApiUserService.class.getSimpleName();

    public static Observable<ApiUserDto> register(ApiUserVo apiUserVo) {
        return Observable.create((ObservableEmitter<ApiUserDto> emitter) -> {
            HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ApiConst.HOST + ApiConst.REGISTER_USER))
                    .newBuilder();
            Request.Builder requestBuilder = new Request.Builder();
            String data = new Gson().toJson(apiUserVo);
            requestBuilder
                    .url(urlBuilder.build())
                    .addHeader("Connection", "close")
                    .post(RequestBody.create(MediaType.parse("application/json"), data));
            OkHttpService.getOkHttpClientInstance().newCall(requestBuilder.build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    emitter.onError(e);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String body = Objects.requireNonNull(response.body()).string();
                    emitter.onNext(new Gson().fromJson(body, ApiUserDto.class));
                    emitter.onComplete();
                }
            });
        }).subscribeOn(Schedulers.newThread());
    }

    public static Observable<ApiUserDto> login(ApiUserLoginVo apiUserLoginVo) {
        return Observable.create((ObservableEmitter<ApiUserDto> emitter) -> {
            HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ApiConst.HOST + ApiConst.LOGIN))
                    .newBuilder();
            Request.Builder requestBuilder = new Request.Builder();
            String data = new Gson().toJson(apiUserLoginVo);
            requestBuilder
                    .url(urlBuilder.build())
                    .addHeader("Connection", "close")
                    .post(RequestBody.create(MediaType.parse("application/json"), data));
            OkHttpService.getOkHttpClientInstance().newCall(requestBuilder.build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    emitter.onError(e);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String body = Objects.requireNonNull(response.body()).string();
                    emitter.onNext(new Gson().fromJson(body, ApiUserDto.class));
                    emitter.onComplete();
                }
            });
        }).subscribeOn(Schedulers.newThread());
    }

    public static Observable<ApiUserDto> getUserFromPhoneNumber(String phoneNumber) {
        return Observable.create((ObservableEmitter<ApiUserDto> emitter) -> {
            HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ApiConst.HOST + ApiConst.GET_USER_FROM_PHONE_NUMBER))
                    .newBuilder();
            Request.Builder requestBuilder = new Request.Builder();
            Map<String, String> map = new HashMap<>();
            map.put("phoneNumber", phoneNumber);
            String data = new Gson().toJson(map);
            requestBuilder
                    .url(urlBuilder.build())
                    .addHeader("Connection", "close")
                    .post(RequestBody.create(MediaType.parse("application/json"), data));
            OkHttpService.getOkHttpClientInstance().newCall(requestBuilder.build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    emitter.onError(e);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String body = Objects.requireNonNull(response.body()).string();
                    emitter.onNext(new Gson().fromJson(body, ApiUserDto.class));
                    emitter.onComplete();
                }
            });
        }).subscribeOn(Schedulers.newThread());
    }
}
