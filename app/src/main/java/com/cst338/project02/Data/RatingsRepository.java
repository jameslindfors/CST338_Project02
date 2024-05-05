package com.cst338.project02.Data;

import android.app.Application;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RatingsRepository {
    private RatingsDAO ratingsDAO;
    private static RatingsRepository repository;

    public RatingsRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        this.ratingsDAO = db.ratingsDAO();
    }

    public static RatingsRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<RatingsRepository> future = AppDatabase.databaseWriteExecutor.submit(
                new Callable<RatingsRepository>() {
                    @Override
                    public RatingsRepository call() throws Exception {
                        return new RatingsRepository(application);
                    }
                }
        );

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
