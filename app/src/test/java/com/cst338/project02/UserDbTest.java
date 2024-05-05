package com.cst338.project02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class UserDbTest {

    private Context mockContext;
    private UserDAO userDao;
    private AppDatabase userDb;

    @Before
    public void createDb() {

        userDb = Room.inMemoryDatabaseBuilder(mockContext, AppDatabase.class).allowMainThreadQueries().build();
        userDao = userDb.userDao();

    }

    @After
    public void closeDb() {
        userDb.close();
    }

    @Test
    public void insertAndRetreiveUser() {
        User user = new User("testUser", "testPass", false);
        userDao.insert(user);

        List<User> foundUsers = userDao.getUsername("testUser");
        assertNotNull(foundUsers);
        assertFalse(foundUsers.isEmpty());
        assertEquals("testUser", foundUsers.get(0).getUsername());
    }

    @Test
    public void loginUser() {
        User user = new User("userLogin", "userPass", true);
        userDao.insert(user);

        User foundUser = userDao.loginUser("userLogin", "userPass");
        assertNotNull(foundUser);
    }

    @Test
    public void getAllUsers() {
        User user1 = new User("user1", "pass1", true);
        User user2 = new User("user2", "pass2", false);
        userDao.insert(user1);
        userDao.insert(user2);

        List<User> users = userDao.getUserLogs();
        assertNotNull(users);
        assertTrue(users.size() >= 2);
    }

    @Test
    public void deleteUser() {
        User user = new User("deleteMe", "deletePass", true);
        userDao.insert(user);
        userDao.deleteUser("deleteMe");

        List<User> users = userDao.getUsername("deleteMe");
        assertTrue(users.isEmpty());
    }

    @Test
    public void updateUser() {
        User user = new User("updateUser", "oldPass", false);
        userDao.insert(user);
        userDao.updateUsername(user.getId(), "newUser");

        List<User> updatedUser = userDao.getUsername("newUser");
        assertNotNull(updatedUser);
        assertFalse(updatedUser.isEmpty());
        assertEquals("newUser", updatedUser.get(0).getUsername());
    }
}
