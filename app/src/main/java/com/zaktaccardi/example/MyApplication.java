package com.zaktaccardi.example;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

/**
 * Extended application.
 */
public class MyApplication extends Application {
    //we statically store the component here so we can retrieve it at any time.
    private static ApplicationComponent component;

    @NonNull
    public static ApplicationComponent getComponent() {
        return component;
    }

    @VisibleForTesting
    public static void setComponent(ApplicationComponent component) {
        MyApplication.component = component;
    }

    @Override
    public void onCreate() {
        injectDependencies();
        super.onCreate();
    }

    void injectDependencies() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .threadingModule(new ThreadingModule())
                .build();
    }
}