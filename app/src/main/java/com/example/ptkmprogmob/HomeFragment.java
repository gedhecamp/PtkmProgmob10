package com.example.ptkmprogmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    FloatingActionButton fabSkp;
    CardView cvMinatBakat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fabSkp = view.findViewById(R.id.floatingActionButtonSkp);
        cvMinatBakat = view.findViewById(R.id.cvMinatBakat);

        fabSkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateSkpActivity.class);
                startActivity(intent);
            }
        });

        cvMinatBakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KategoriSkpActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
