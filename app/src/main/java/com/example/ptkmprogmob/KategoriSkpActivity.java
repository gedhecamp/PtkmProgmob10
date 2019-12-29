package com.example.ptkmprogmob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.RetrofitClient;
import com.example.ptkmprogmob.APIHelper.UtilsApi;
import com.example.ptkmprogmob.Adapter.KepanitiaanAdapter;
import com.example.ptkmprogmob.Adapter.SkpAdapter;
import com.example.ptkmprogmob.Model.DetailSkp;
import com.example.ptkmprogmob.Model.Kepanitiaan;
import com.example.ptkmprogmob.Model.Skp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KategoriSkpActivity extends AppCompatActivity {

    List<Skp> skpList = new ArrayList<Skp>();
    RecyclerView recyclerView;
    SkpAdapter adapter;
    Context mContext;
    String kategori;
    String id_user="";
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_skp);
        mContext = this;

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        id_user = user.get(UserSessionManager.KEY_ID);
        Intent intent =getIntent();
        kategori = intent.getStringExtra("kategori");
        skp();

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Kategori SKP");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setRecycler(){

        recyclerView = findViewById(R.id.skpRecycler);
        Log.e("sdf",skpList.toString());

        recyclerView.setVisibility(View.VISIBLE);
        adapter = new SkpAdapter(mContext, skpList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        recyclerView.setAdapter(adapter);
    }

    private void skp(){
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .skpList(kategori, id_user)
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
                        Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

}
