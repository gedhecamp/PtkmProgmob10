package com.example.ptkmprogmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    EditText etNim;
    EditText etPassword;
    Button btnLogin;
    TextView tvRegister;
    ProgressDialog loading;
    UserSessionManager session;


    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        initComponents();
        session = new UserSessionManager(this);
    }

    private void initComponents() {
        etNim = (EditText) findViewById(R.id.nim);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.loginButton);
        tvRegister = (TextView) findViewById(R.id.singUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
                finish();
            }
        });
    }

    private void requestLogin(){
        mApiService.loginRequest(etNim.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    // Jika LoginActivity berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    Toast.makeText(mContext, "Login Success", Toast.LENGTH_SHORT).show();
                                    final String id = jsonRESULTS.getJSONObject("user").getString("id");
                                    final String nim = jsonRESULTS.getJSONObject("user").getString("nim");
                                    final String nama = jsonRESULTS.getJSONObject("user").getString("name");
                                    final String email = jsonRESULTS.getJSONObject("user").getString("email");
                                    final String phone = jsonRESULTS.getJSONObject("user").getString("phone");
                                    session.createUserLoginSession(id, nim, nama, email, phone);
//                                    int id = jsonRESULTS.getJSONObject("user").getInt("id");
//                                    Log.d("debug","id : "+id);
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    intent.putExtra("nama", nama);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Jika LoginActivity gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
