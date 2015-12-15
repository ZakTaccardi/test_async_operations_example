package com.zaktaccardi.example;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Module for providing data dependencies.
 */
@Module
public class DataModule {

    @Singleton
    @Provides
    FavoriteColorRepo provideFavoriteColorRepo(
            @Named(ThreadingModule.MAIN_THREAD) Scheduler main,
            @Named(ThreadingModule.IO_THREAD) Scheduler io,
            @Named(ThreadingModule.COMPUTATION_THREAD) Scheduler comp
    ) {
        return new FavoriteColorRepoImpl(main, io, comp);
    }
}
