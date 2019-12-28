package com.example.ptkmprogmob;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {

    UserSessionManager session;
    FrameLayout rootView;
    Button logoutButton;
    Button btnEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = (FrameLayout) inflater.inflate(R.layout.fragment_account, container, false);

        session = new UserSessionManager(getActivity().getApplicationContext());

        TextView tvNama = (TextView) rootView.findViewById(R.id.nama);

        TextView tvNim = (TextView) rootView.findViewById(R.id.nim);

        logoutButton = (Button) rootView.findViewById(R.id.logoutButton);

        btnEdit = (Button) rootView.findViewById(R.id.editAccountButton);


        if (session.checkLogin()){
            getActivity().finish();
        }
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(UserSessionManager.KEY_NAME);
        String nim = user.get(UserSessionManager.KEY_NIM);
        tvNama.setText(name.toString());
        tvNim.setText(nim.toString());

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                getActivity().finish();
                Toast.makeText(getActivity(), "Logout Berhasil", Toast.LENGTH_SHORT).show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditAccountActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }
}
