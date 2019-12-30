package com.example.ptkmprogmob;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.HashMap;

public class EditAccountActivity extends AppCompatActivity {

    String id="";
    EditText etNim;
    EditText etNama;
    EditText etEmail;
    EditText etPhone;
    ProgressDialog loading;
    UserSessionManager session;
    Button saveButton;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        initComponents();

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Edit Profile");

        session = new UserSessionManager(getApplicationContext());

        EditText editNim = (EditText) findViewById(R.id.editAccountNim);
        EditText editNama = (EditText) findViewById(R.id.editAccountFullName);
        EditText editEmail = (EditText) findViewById(R.id.editAccountEmail);
        EditText editPhone = (EditText) findViewById(R.id.editAccountPhone);

        HashMap<String, String> user = session.getUserDetails();
        id = user.get(UserSessionManager.KEY_ID);
        String nim = user.get(UserSessionManager.KEY_NIM);
        String name = user.get(UserSessionManager.KEY_NAME);
        String email = user.get(UserSessionManager.KEY_EMAIL);
        String phone = user.get(UserSessionManager.KEY_PHONE);
        editNim.setText(nim.toString());
        editNama.setText(name.toString());
        editEmail.setText(email.toString());
        editPhone.setText(phone.toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponents() {
        etNim = (EditText) findViewById(R.id.editAccountNim);
        etNama = (EditText) findViewById(R.id.editAccountFullName);
        etEmail = (EditText) findViewById(R.id.editAccountEmail);
        etPhone = (EditText) findViewById(R.id.editAccountPhone);
        saveButton = (Button) findViewById(R.id.saveAccountButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestUpdateUser();
            }
        });
    }

    private void requestUpdateUser(){
        mApiService.updateUser(id,
                etNim.getText().toString(),
                etNama.getText().toString(),
                etEmail.getText().toString(),
                etPhone.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "Update Success", Toast.LENGTH_SHORT).show();
                                    final String id = jsonRESULTS.getJSONObject("user").getString("id");
                                    final String nim = jsonRESULTS.getJSONObject("user").getString("nim");
                                    final String nama = jsonRESULTS.getJSONObject("user").getString("name");
                                    final String email = jsonRESULTS.getJSONObject("user").getString("email");
                                    final String phone = jsonRESULTS.getJSONObject("user").getString("phone");
                                    final String type_user = jsonRESULTS.getJSONObject("user").getString("type_user");
                                    session.createUserLoginSession(id, nim, nama, email, phone, type_user);
                                    //startActivity(new Intent(mContext, MainActivity.class));
                                    finish();
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
