package com.zaktaccardi.example;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import dagger.Module;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Tests for the {@link FavoriteColorRepoImpl} class.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class FavoriteColorRepoImplTest {
    FavoriteColorRepoImpl favoriteColorRepoImpl;

    @Before
    public void setUp() throws Exception {
        //let's set a new component for our application
        final ApplicationComponent applicationComponent =
                DaggerApplicationComponent.builder()
                        .applicationModule(
                                new ApplicationModule(
                                        (MyApplication) RuntimeEnvironment.application
                                )
                        )
                        //and supply a different threading module.
                        .threadingModule(new MockThreadingModule())
                        .build();

        MyApplication.setComponent(applicationComponent);

        favoriteColorRepoImpl = (FavoriteColorRepoImpl) MyApplication.getComponent().getFavoriteColorRepo();
    }

    @Test
    public void testSaveFavoriteColorAsync() throws Exception {
        final String expected = "green"; //1

        favoriteColorRepoImpl.saveFavoriteColorAsync(expected); //2
        final String actual = favoriteColorRepoImpl.getFavoriteColor().toBlocking().first(); //3

        Assert.assertThat("Favorite color should now be green after saving",
                actual, Matchers.equalTo(expected)); //4
    }

    /**
     * A mock threading module where all schedulers run on the main thread.
     */
    @Module
    public static class MockThreadingModule extends ThreadingModule {
        @Override
        public Scheduler provideMainThreadScheduler() {
            return AndroidSchedulers.mainThread();
        }

        @Override
        public Scheduler provideIoScheduler() {
            return AndroidSchedulers.mainThread();
        }

        @Override
        public Scheduler provideCompScheduler() {
            return AndroidSchedulers.mainThread();
        }
    }
}