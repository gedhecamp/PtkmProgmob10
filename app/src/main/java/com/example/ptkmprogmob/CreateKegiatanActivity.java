package com.example.ptkmprogmob;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.RetrofitClient;
import com.example.ptkmprogmob.APIHelper.UtilsApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateKegiatanActivity extends AppCompatActivity {

    DatePickerDialog picker;
    EditText etNamaKepanitiaan;
    EditText etTanggalKepanitiaan;
    EditText etTempatKepanitian;
    EditText etDescKepanitiaan;
    ImageView imageKepanitiaan;
    Button uploadFile;
    Button saveSkp;

    String nama_kepanitiaan, tanggal_kepanitiaan, tempat_kepanitiaan, desc_kepanitiaan;

    MultipartBody.Part imageMB;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_kegiatan);

        etNamaKepanitiaan = (EditText) findViewById(R.id.editNamaKepanitiaan);
        etTempatKepanitian = (EditText) findViewById(R.id.editTempatKepanitiaan);
        etDescKepanitiaan = (EditText) findViewById(R.id.editDescKepanitiaan);
        imageKepanitiaan = (ImageView) findViewById(R.id.imageKepanitiaan);
        uploadFile = (Button) findViewById(R.id.uploadImage);
        saveSkp = (Button) findViewById(R.id.saveKepanitiaan);

        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        saveSkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createKepaitiaan();
            }
        });

        etTanggalKepanitiaan = (EditText) findViewById(R.id.editTanggalKepanitiaan);
        etTanggalKepanitiaan.setInputType(InputType.TYPE_NULL);
        etTanggalKepanitiaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CreateKegiatanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etTanggalKepanitiaan.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Create Kegiatan");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void createKepaitiaan(){

        nama_kepanitiaan = etNamaKepanitiaan.getText().toString();
        tanggal_kepanitiaan = etTanggalKepanitiaan.getText().toString();
        tempat_kepanitiaan = etTempatKepanitian.getText().toString();
        desc_kepanitiaan = etDescKepanitiaan.getText().toString();

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("nama_kegiatan", RequestBody.create(okhttp3.MultipartBody.FORM, nama_kepanitiaan));
        map.put("tanggal_kegiatan", RequestBody.create(okhttp3.MultipartBody.FORM, tanggal_kepanitiaan));
        map.put("tempat_kegiatan", RequestBody.create(okhttp3.MultipartBody.FORM, tempat_kepanitiaan));
        map.put("desc_kegiatan", RequestBody.create(okhttp3.MultipartBody.FORM, desc_kepanitiaan));

        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .createKegiatan(imageMB,map)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getApplicationContext(), "Create Kepanitiaan Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            try {
                Bitmap bitmapFoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                bitmapFoto = scaleDown(bitmapFoto,true);
                imageKepanitiaan.setImageBitmap(bitmapFoto);

                File file = createTempFile(bitmapFoto);
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                imageMB = MultipartBody.Part.createFormData("image_kegiatan", file.getName(), reqFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File createTempFile(Bitmap bitmap) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , "image.PNG");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,0, bos);
        byte[] bitmapdata = bos.toByteArray();
        //write the bytes in file

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static Bitmap scaleDown(Bitmap realImage,
                                   boolean filter) {
        if (realImage.getWidth() > 1500 || realImage.getHeight() > 1500){
            Bitmap newbitmap = Bitmap.createScaledBitmap(realImage, 600,
                    800, filter);
            return newbitmap;
        }
        else{
            return realImage;
        }
    }
}
