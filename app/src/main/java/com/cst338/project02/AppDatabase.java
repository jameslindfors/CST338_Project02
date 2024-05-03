package com.cst338.project02;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String USER_TABLE = "user_log";
    private static final String DATABASE_NAME = "User.DB";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract UserDAO userDao();

    public static AppDatabase getInstance(Context context){
        if (instance == null){
            synchronized (AppDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return instance;
    }


    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // Prepopulate the data
                    UserDAO dao = instance.userDao();
//                    dao.deleteAll();

                    User user1 = new User("testuser1","testuser1", false);
                    User user2 = new User("admin2","admin2", true);

                    dao.insert(user1);
                    dao.insert(user2);
                }
            });


        }
    };


}
