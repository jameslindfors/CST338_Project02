package com.cst338.project02;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cst338.project02.databinding.FragmentProfileTabBinding;

public class ProfileTab extends Fragment {

    private FragmentProfileTabBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileTabBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Button logoutBtn = view.findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button deleteAccount = view.findViewById(R.id.deleteAccount);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(getContext());
                        UserDAO dao = db.userDao();


//                        dao.deleteUser();

                    }
                }).start();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

}