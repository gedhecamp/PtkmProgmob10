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
    CardView cvMinatBakat, cvOrganisasi, cvPM, cvIlmiah, cvPartisipan, cvKepanitiaan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fabSkp = view.findViewById(R.id.floatingActionButtonSkp);
        cvMinatBakat = view.findViewById(R.id.cvMinatBakat);
        cvOrganisasi = view.findViewById(R.id.cvOrganisasi);
        cvPM = view.findViewById(R.id.cvPm);
        cvIlmiah = view.findViewById(R.id.cvIlmiah);
        cvPartisipan = view.findViewById(R.id.cvPartisipan);
        cvKepanitiaan = view.findViewById(R.id.cvKepanitiaan);

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
                intent.putExtra("kategori","Minat dan Bakat");
                startActivity(intent);
            }
        });

        cvOrganisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KategoriSkpActivity.class);
                intent.putExtra("kategori","Organisasi");
                startActivity(intent);
            }
        });

        cvPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KategoriSkpActivity.class);
                intent.putExtra("kategori","Pengabdian Masyarakat");
                startActivity(intent);
            }
        });

        cvIlmiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KategoriSkpActivity.class);
                intent.putExtra("kategori","Penalaran dan Ilmiah");
                startActivity(intent);
            }
        });

        cvPartisipan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KategoriSkpActivity.class);
                intent.putExtra("kategori","Partisipan");
                startActivity(intent);
            }
        });

        cvKepanitiaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KategoriSkpActivity.class);
                intent.putExtra("kategori","Kepanitiaan");
                startActivity(intent);
            }
        });

        return view;
    }
}
