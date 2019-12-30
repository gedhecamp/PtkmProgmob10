package com.example.ptkmprogmob;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.RetrofitClient;
import com.example.ptkmprogmob.APIHelper.UtilsApi;
import com.example.ptkmprogmob.Model.Home;
import com.example.ptkmprogmob.Model.Kepanitiaan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    FloatingActionButton fabSkp;
    CardView cvMinatBakat, cvOrganisasi, cvPM, cvIlmiah, cvPartisipan, cvKepanitiaan;
    TextView countTotal, mikat, organisasi, pm, ilmiah, partisipan, kepanitiaan;

    String id_user="";
    UserSessionManager session;
    String kategori;

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
        countTotal = view.findViewById(R.id.totalSkp);
        mikat = view.findViewById(R.id.mikatPoint);
        organisasi = view.findViewById(R.id.organisasiPoint);
        pm = view.findViewById(R.id.pengabdianPoint);
        ilmiah = view.findViewById(R.id.ilmiahPoint);
        partisipan = view.findViewById(R.id.partisipanPoint);
        kepanitiaan = view.findViewById(R.id.kepanitiaanPoint);

        session = new UserSessionManager(getContext());
        HashMap<String, String> user = session.getUserDetails();
        id_user = user.get(UserSessionManager.KEY_ID);

        countSkp();
        countMikat();
        countOrganisasi();
        countPM();
        countIlmiah();
        countPartisipan();
        countKepanitiaan();


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

    private void countSkp() {
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .countSkp(id_user)
                .enqueue(new Callback<List<Home>>() {
                    @Override
                    public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {


                        if (response.body().get(0).getCountSkp()!=null){

                            countTotal.setText(response.body().get(0).getCountSkp().toString());
                        }
                        else {
                            countTotal.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Home>> call, Throwable t) {

                    }
                });
    }

    private void countMikat() {
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .countKategori("Minat dan Bakat",id_user)
                .enqueue(new Callback<List<Home>>() {
                    @Override
                    public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {

                        if (response.body().get(0).getCountKategori()!=null){

                            mikat.setText(response.body().get(0).getCountKategori().toString());
                        }
                        else {
                            mikat.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Home>> call, Throwable t) {

                    }
                });
    }

    private void countOrganisasi() {
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .countKategori("Organisasi",id_user)
                .enqueue(new Callback<List<Home>>() {
                    @Override
                    public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {

                        if (response.body().get(0).getCountKategori()!=null){

                            organisasi.setText(response.body().get(0).getCountKategori().toString());
                        }
                        else {
                            organisasi.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Home>> call, Throwable t) {

                    }
                });
    }

    private void countPM() {
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .countKategori("Pengabdian Masyarakat",id_user)
                .enqueue(new Callback<List<Home>>() {
                    @Override
                    public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {

                        if (response.body().get(0).getCountKategori()!=null){

                            pm.setText(response.body().get(0).getCountKategori().toString());
                        }
                        else {
                            pm.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Home>> call, Throwable t) {

                    }
                });
    }

    private void countIlmiah() {
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .countKategori("Penalaran dan Ilmiah",id_user)
                .enqueue(new Callback<List<Home>>() {
                    @Override
                    public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {

                        if (response.body().get(0).getCountKategori()!=null){

                            ilmiah.setText(response.body().get(0).getCountKategori().toString());
                        }
                        else {
                            ilmiah.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Home>> call, Throwable t) {

                    }
                });
    }

    private void countPartisipan() {
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .countKategori("Partisipan",id_user)
                .enqueue(new Callback<List<Home>>() {
                    @Override
                    public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {

                        if (response.body().get(0).getCountKategori()!=null){

                            partisipan.setText(response.body().get(0).getCountKategori().toString());
                        }
                        else {
                            partisipan.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Home>> call, Throwable t) {

                    }
                });
    }

    private void countKepanitiaan() {
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                    .countKategori("Kepanitiaan",id_user)
                .enqueue(new Callback<List<Home>>() {
                    @Override
                    public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {

                        if (response.body().get(0).getCountKategori()!=null){

                            kepanitiaan.setText(response.body().get(0).getCountKategori().toString());
                        }
                        else {
                            kepanitiaan.setText("0");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Home>> call, Throwable t) {

                    }
                });
    }
}
