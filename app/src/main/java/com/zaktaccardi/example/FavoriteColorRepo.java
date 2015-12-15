package com.zaktaccardi.example;


import rx.Observable;

/**
 * A repository to store someone's favorite color.
 */
public interface FavoriteColorRepo {

    /**
     * Loads the user's favorite color.
     * @return ex- "blue"
     */
    public Observable<String> getFavoriteColor();

    /**
     * Will asynchronously save the users favorite color.
     */
    public void saveFavoriteColorAsync(String favoriteColor);

}
