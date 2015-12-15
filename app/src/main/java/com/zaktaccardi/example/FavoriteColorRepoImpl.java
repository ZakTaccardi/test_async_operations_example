package com.zaktaccardi.example;


import java.util.concurrent.atomic.AtomicReference;

import rx.Observable;
import rx.Scheduler;
import rx.exceptions.Exceptions;

/**
 * This is a fake database class that we will use to simulate storing and retrieving data from the
 * disk with.
 */
public class FavoriteColorRepoImpl implements FavoriteColorRepo {
    private final Scheduler main;
    private final Scheduler io;
    private final Scheduler comp;

    private AtomicReference<String> favoriteColor;

    public FavoriteColorRepoImpl(Scheduler main, Scheduler io, Scheduler comp) {
        this.main = main;
        this.io = io;
        this.comp = comp;

        favoriteColor = new AtomicReference<>("blue");
    }

    public Observable<String> getFavoriteColor() {
        return Observable.defer(() -> Observable.just(favoriteColor.get()));

    }

    @Override
    public void saveFavoriteColorAsync(final String color) {
        Observable.just(color)
                .map(s -> {
                    simulateLongRunningOperation();
                    favoriteColor.set(s); //"saves" the favorite color
                    return null; //nothing needs to be returned here
                })
                .subscribeOn(io) //io thread
                .subscribe();
    }

    private void simulateLongRunningOperation() {
        try {
            //Let's pretend it took a while for us to save our favorite color.
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Exceptions.propagate(e);
        }
    }
}
