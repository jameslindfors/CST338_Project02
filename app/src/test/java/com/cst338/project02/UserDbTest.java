package com.cst338.project02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.cst338.project02.Data.AppDatabase;
import com.cst338.project02.Data.User;
import com.cst338.project02.Data.UserDAO;

import com.cst338.project02.Data.AppDatabase;
import com.cst338.project02.Data.User;
import com.cst338.project02.Data.UserDAO;


@RunWith(MockitoJUnitRunner.class)
public class UserDbTest {

    @Mock
    private Context mockContext;
    private UserDAO userDao;
    private AppDatabase userDb;

    @Before
    public void createDb() {

        mockContext = mock(Context.class);


        userDb = Room.inMemoryDatabaseBuilder(mockContext, AppDatabase.class).fallbackToDestructiveMigration().build();
        userDao = userDb.userDao();

    }

    @After
    public void closeDb() {
        userDb.close();
    }
    
    @Test
    public void insertAndRetreiveUser(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User("testUser", "testPass", false);

                userDao.insert(user);
                List<User> foundUsers = userDao.getUsername("testUser");

                InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
                    @Override
                    public void run() {
                        assertNotNull(foundUsers);
                        assertFalse(foundUsers.isEmpty());
                        assertEquals("testUser", foundUsers.get(0).getUsername());
                    }
                });

            }
        });
    }

    @Test
    public void loginUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User("userLogin", "userPass", true);

                userDao.insert(user);

                User foundUser = userDao.loginUser("userLogin", "userPass");
                InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
                    @Override
                    public void run() {
                        assertNotNull(foundUser);
                    }
                });
            }


        });



    }

    @Test
    public void getAllUsers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user1 = new User("user1", "pass1", true);
                User user2 = new User("user2", "pass2", false);
                userDao.insert(user1);
                userDao.insert(user2);

                List<User> users = userDao.getUserLogs();
                InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
                    @Override
                    public void run() {
                        assertNotNull(users);
                        assertTrue(users.size() >= 2);
                    }
                });
            }


        });


    }

    @Test
    public void deleteUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User("deleteMe", "deletePass", true);
                userDao.insert(user);
                userDao.deleteUser("deleteMe");

                List<User> users = userDao.getUsername("deleteMe");
                InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
                    @Override
                    public void run() {
                        assertTrue(users.isEmpty());
                    }
                });
            }


        });


    }

    @Test
    public void updateUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User("updateUser", "oldPass", false);
                userDao.insert(user);
                userDao.updateUsername(user.getId(), "newUser");

                List<User> updatedUser = userDao.getUsername("newUser");
                InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
                    @Override
                    public void run() {
                        assertNotNull(updatedUser);
                        assertFalse(updatedUser.isEmpty());
                        assertEquals("newUser", updatedUser.get(0).getUsername());
                    }
                });
            }


        });


    }
}
