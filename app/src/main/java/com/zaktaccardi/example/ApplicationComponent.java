package com.zaktaccardi.example;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component for injecting application-wide dependencies
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @Singleton
    FavoriteColorRepo getFavoriteColorRepo();
}
