package com.example.toutiao;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by zhangjinbo on 17-8-30.
 */

public class ErrorAction {

    @NonNull
    public static Consumer<Throwable> error() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                if(BuildConfig.DEBUG) {
                    throwable.printStackTrace();
                }
            }
        };
    }

    public static void print(@NonNull Throwable throwable) {
        if(BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
    }
}
