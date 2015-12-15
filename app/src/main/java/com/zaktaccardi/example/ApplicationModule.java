package com.zaktaccardi.example;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module for injecting application-wide dependencies, such as a {@link Context}.
 */
@Module(includes = {ThreadingModule.class, DataModule.class})
public class ApplicationModule {
    private final MyApplication myApplication;

    public ApplicationModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return myApplication;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return myApplication;
    }
}