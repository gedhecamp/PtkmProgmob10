package com.example.ptkmprogmob;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

public class UpdateKegiatanActivity extends AppCompatActivity {

    DatePickerDialog picker;
    EditText etNamaKepanitiaan;
    EditText etTanggalKepanitiaan;
    EditText etTempatKepanitian;
    EditText etDescKepanitiaan;
    ImageView imageKepanitiaan;
    Button uploadFile;
    Button saveSkp;
    String id_kegiatan1="";

    String nama_kepanitiaan, tanggal_kepanitiaan, tempat_kepanitiaan, desc_kepanitiaan;

    MultipartBody.Part imageMB;
    private static int RESULT_LOAD_IMAGE = 1;

    public static final String EXTRA_ID_KEGIATAN = "id_kegiatan";
    public static final String EXTRA_NAMA_KEGIATAN = "nama_kegiatan";
    public static final String EXTRA_TANGGAL_KEGIATAN = "tanggal_kegiatan";
    public static final String EXTRA_TEMPAT_KEGIATAN = "tempat_kegiatan";
    public static final String EXTRA_DESC_KEGIATAN = "desc_kegiatan";
    public static final String EXTRA_IMAGE_KEGIATAN = "image_kegiatan";

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

        id_kegiatan1 = getIntent().getStringExtra(EXTRA_ID_KEGIATAN);
        final String nama_kegiatan = getIntent().getStringExtra(EXTRA_NAMA_KEGIATAN);
        final String tanggal_kegiatan = getIntent().getStringExtra(EXTRA_TANGGAL_KEGIATAN);
        final String tempat_kegiatan = getIntent().getStringExtra(EXTRA_TEMPAT_KEGIATAN);
        final String desc_kegiatan = getIntent().getStringExtra(EXTRA_DESC_KEGIATAN);
        final String image_kegiatan = getIntent().getStringExtra(EXTRA_IMAGE_KEGIATAN);

        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        saveSkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateKegiatan();
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
                picker = new DatePickerDialog(UpdateKegiatanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etTanggalKepanitiaan.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        etNamaKepanitiaan.setText(nama_kegiatan);
        etTanggalKepanitiaan.setText(tanggal_kegiatan);
        etTempatKepanitian.setText(tempat_kegiatan);
        etDescKepanitiaan.setText(desc_kegiatan);

        Glide.with(this)
                .load(UtilsApi.BASE_URL_IMAGE+image_kegiatan)
                .centerCrop()
                .placeholder(R.drawable.blank)
                .into(imageKepanitiaan);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Edit Kegiatan");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void updateKegiatan(){

        nama_kepanitiaan = etNamaKepanitiaan.getText().toString();
        tanggal_kepanitiaan = etTanggalKepanitiaan.getText().toString();
        tempat_kepanitiaan = etTempatKepanitian.getText().toString();
        desc_kepanitiaan = etDescKepanitiaan.getText().toString();

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("id_kegiatan", RequestBody.create(MultipartBody.FORM, id_kegiatan1));
        map.put("nama_kegiatan", RequestBody.create(MultipartBody.FORM, nama_kepanitiaan));
        map.put("tanggal_kegiatan", RequestBody.create(MultipartBody.FORM, tanggal_kepanitiaan));
        map.put("tempat_kegiatan", RequestBody.create(MultipartBody.FORM, tempat_kepanitiaan));
        map.put("desc_kegiatan", RequestBody.create(MultipartBody.FORM, desc_kepanitiaan));

        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .updateKegiatan(imageMB,map)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getApplicationContext(), "Kepanitiaan Update Success", Toast.LENGTH_SHORT).show();
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
