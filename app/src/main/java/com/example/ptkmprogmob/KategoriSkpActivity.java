package com.example.ptkmprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.RetrofitClient;
import com.example.ptkmprogmob.APIHelper.UtilsApi;
import com.example.ptkmprogmob.Adapter.KepanitiaanAdapter;
import com.example.ptkmprogmob.Adapter.SkpAdapter;
import com.example.ptkmprogmob.Model.DetailSkp;
import com.example.ptkmprogmob.Model.Kepanitiaan;
import com.example.ptkmprogmob.Model.Skp;

import java.util.ArrayList;
import java.util.List;

public class KategoriSkpActivity extends AppCompatActivity {

    List<Skp> skpList = new ArrayList<>();
    RecyclerView recyclerView;
    KepanitiaanAdapter adapter;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_skp);

        mContext = this;

        listSkp();

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Kategori SKP");
    }

    private void listSkp(){
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .listSkp()
                .enqueue(new Callback<List<Skp>>() {
                    @Override
                    public void onResponse(Call<List<Skp>> call, Response<List<Skp>> response) {
                        skpList.clear();
                        try {
                            skpList.addAll(response.body());
                            setRecycler();
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Skp>> call, Throwable t) {

                    }
                });
    }

    private void setRecycler(){

        recyclerView = findViewById(R.id.skpRecycler);
        Log.e("sdf",skpList.toString());

        //adapter = new SkpAdapter(skpList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
