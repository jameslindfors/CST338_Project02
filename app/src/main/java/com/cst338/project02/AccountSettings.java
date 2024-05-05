package com.cst338.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.cst338.project02.databinding.ActivityAccountSettingsBinding;

public class AccountSettings extends AppCompatActivity {

    ActivityAccountSettingsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountSettingsBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        binding.changeUsernameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goEditUsernamePage = new Intent(AccountSettings.this, EditUsername.class);
                startActivity(goEditUsernamePage);
            }
        });


        binding.deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AccountSettings.this);
                builder.setTitle("Delete Account");
                builder.setMessage("Are you sure you want to delete your account?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccount();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();  // Close the dialog without doing anything
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });




        binding.backButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProfilePage = new Intent(AccountSettings.this, ProfilePage.class);
                startActivity(goProfilePage);
            }
        });
    }

    public void deleteAccount(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                UserDAO dao = db.userDao();

                SharedPreferences preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                String username = preferences.getString("username", "DefaultUser");

                dao.deleteUser(username);

            }
        }).start();
        Intent intent = new Intent(AccountSettings.this, MainActivity.class);
        startActivity(intent);
    }
}