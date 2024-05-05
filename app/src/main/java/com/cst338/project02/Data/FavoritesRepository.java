package com.cst338.project02.Data;

import android.app.Application;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FavoritesRepository {
    private FavoritesDAO favoritesDAO;
    private static FavoritesRepository repository;

    public FavoritesRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        this.favoritesDAO = db.favoritesDAO();
    }

    public static FavoritesRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<FavoritesRepository> future = AppDatabase.databaseWriteExecutor.submit(
                new Callable<FavoritesRepository>() {
                    @Override
                    public FavoritesRepository call() throws Exception {
                        return new FavoritesRepository(application);
                    }
                }
        );

        try {
            future.get();
        } catch ( InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
