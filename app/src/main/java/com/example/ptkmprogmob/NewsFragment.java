package com.example.ptkmprogmob;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.RetrofitClient;
import com.example.ptkmprogmob.APIHelper.UtilsApi;
import com.example.ptkmprogmob.Adapter.KepanitiaanAdapter;
import com.example.ptkmprogmob.Model.Kepanitiaan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    FloatingActionButton fabKegiatan;
    RecyclerView recyclerView;
    KepanitiaanAdapter adapter;
    List<Kepanitiaan> kepanitiaanList = new ArrayList<Kepanitiaan>();
    View rootView;

    String type_user="";
    UserSessionManager session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news, container, false);

        fabKegiatan = rootView.findViewById(R.id.floatingActionButtonKegiatan);

        fabKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateKegiatanActivity.class);
                startActivity(intent);
            }
        });



        session = new UserSessionManager(getContext());
        HashMap<String, String> user = session.getUserDetails();
        type_user = user.get(UserSessionManager.KEY_TYPE);
        Log.e("error", type_user);

        if (type_user.equals("1")){
            fabKegiatan.show();
        } else {
            fabKegiatan.hide();
        }

        kepanitiaan();
        return rootView;
    }

    private void kepanitiaan() {
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .listKegiatan()
                .enqueue(new Callback<List<Kepanitiaan>>() {
                    @Override
                    public void onResponse(Call<List<Kepanitiaan>> call, Response<List<Kepanitiaan>> response) {
                        kepanitiaanList.clear();
                        try {
                            kepanitiaanList.addAll(response.body());

                            DBHelper dbHelper = new DBHelper(getContext());
                            dbHelper.deleteKegiatan();

                            for (Kepanitiaan kepanitiaan:kepanitiaanList){
                                dbHelper.insertKegiatan(kepanitiaan.getId_kegiatan(), kepanitiaan.getNama_kegiatan(), kepanitiaan.getTanggal_kegiatan(),
                                        kepanitiaan.getTempat_kegiatan(), kepanitiaan.getDesc_kegiatan(), kepanitiaan.getImage_kegiatan());
                            }

                            setRecycler();
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Kepanitiaan>> call, Throwable t) {
                        callFavoriteLocal();

                    }
                });
    }

    private void setRecycler(){

        recyclerView = rootView.findViewById(R.id.kepanitiaanRicycle);
        Log.e("sdf",kepanitiaanList.toString());

        adapter = new KepanitiaanAdapter(rootView.getContext(), kepanitiaanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void callFavoriteLocal() {
        DBHelper dbHelper = new DBHelper(getContext());
        kepanitiaanList = dbHelper.selectKegiatan();

        setRecycler();
    }
}