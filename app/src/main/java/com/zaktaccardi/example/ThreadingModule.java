package com.zaktaccardi.example;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A dagger module to decouple threading even further!
 */
@Module
public class ThreadingModule {
    public static final String MAIN_THREAD = "main";
    public static final String IO_THREAD = "io";
    public static final String COMPUTATION_THREAD = "comp";

    @Provides
    @Named(MAIN_THREAD)
    @Singleton
    public Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named(IO_THREAD)
    @Singleton
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named(COMPUTATION_THREAD)
    @Singleton
    public Scheduler provideCompScheduler() {
        return Schedulers.computation();
    }
}
