package com.cst338.project02;

import android.app.Application;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UserRepository {

    private UserDAO userDAO;

    private ArrayList<User> allUsers;

    private static UserRepository repository;

    public UserRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        this.userDAO = db.userDao();
        this.allUsers = (ArrayList<User>) this.userDAO.getUserLogs();
    }

    public static UserRepository getRepository(Application application){
        if(repository != null){
            return repository;
        }
        Future<UserRepository> future = AppDatabase.databaseWriteExecutor.submit(
                new Callable<UserRepository>() {
                    @Override
                    public UserRepository call() throws Exception {
                        return new UserRepository(application);
                    }
                }
        );

        try{
            future.get();
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> getAllUsers(){
        Future<ArrayList<User>> future = AppDatabase.databaseWriteExecutor.submit(

                new Callable<ArrayList<User>>() {
                    @Override
                    public ArrayList<User> call() throws Exception {
                        return (ArrayList<User>) userDAO.getUserLogs();
                    }
                }

        );
        try{
            return future.get();
        } catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public void insertUser(User user){
        AppDatabase.databaseWriteExecutor.execute(()->{
            userDAO.insert(user);
        });
    }
}
