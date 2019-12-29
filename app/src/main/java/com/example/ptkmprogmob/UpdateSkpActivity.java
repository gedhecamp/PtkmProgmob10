package com.example.ptkmprogmob;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.RetrofitClient;
import com.example.ptkmprogmob.APIHelper.UtilsApi;
import com.example.ptkmprogmob.Model.DetailSkp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSkpActivity extends AppCompatActivity {

    DatePickerDialog picker;
    EditText etNamaKegiatan;
    EditText etTglAwal;
    EditText etTglAkhir;
    EditText etTempatKegiatan;
    ImageView ivBuktiSkp;
    Spinner spinerDetailSkp;
    String detailskp;
    Button uploadBuktiSkp;
    Button saveSkp;
    String id_user="";
    String id_skp1="";
    UserSessionManager session;
    Context mContext;

    public static final String EXTRA_ID_SKP = "id_skp1";
    public static final String EXTRA_NAMA_SKP = "nama";
    public static final String EXTRA_TANGGAL_AWAL = "tgl_awal";
    public static final String EXTRA_TANGGAL_AKHIR = "tgl_akhir";
    public static final String EXTRA_TEMPAT_SKP = "tempat";
    public static final String EXTRA_BUKTI_SKP = "bukti_skp";
    public static final String EXTRA_ID_DETAIL = "id_detail";
    public static final String EXTRA_ID_USER = "id_user";
    public static final String EXTRA_KATEGORI_SKP = "kategori";
    public static final String EXTRA_TINGKAT_SKP = "tingkat";
    public static final String EXTRA_KETERANGAN_SKP = "keterangan";
    public static final String EXTRA_POINT_SKP = "point";

    private List<DetailSkp> detailSkpList = new ArrayList<>();

    MultipartBody.Part imageMB1;
    private static int RESULT_LOAD_IMAGE = 1;

    String nama_kegiatan, tgl_awal, tgl_akhir, tempat_kegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_skp);
        mContext = this;

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        id_user = user.get(UserSessionManager.KEY_ID);

        etNamaKegiatan = (EditText) findViewById(R.id.editNamaKegiatan);
        etTempatKegiatan = (EditText) findViewById(R.id.editTempat);
        ivBuktiSkp = (ImageView) findViewById(R.id.imageBuktiSkp);
        uploadBuktiSkp = (Button) findViewById(R.id.uploadBuktiSkp);
        saveSkp = (Button) findViewById(R.id.saveKepanitiaan);

        id_skp1 = getIntent().getStringExtra(EXTRA_ID_SKP);
        final String nama_skp = getIntent().getStringExtra(EXTRA_NAMA_SKP);
        final String tgl_awal_skp = getIntent().getStringExtra(EXTRA_TANGGAL_AWAL);
        final String tgl_akhir_skp = getIntent().getStringExtra(EXTRA_TANGGAL_AKHIR);
        final String tempat_skp = getIntent().getStringExtra(EXTRA_TEMPAT_SKP);
        final String bukti_skp = getIntent().getStringExtra(EXTRA_BUKTI_SKP);
        final String id_detail = getIntent().getStringExtra(EXTRA_ID_DETAIL);
        final String id_user = getIntent().getStringExtra(EXTRA_ID_USER);
        final String kategori_skp = getIntent().getStringExtra(EXTRA_KATEGORI_SKP);
        final String tingkat_skp = getIntent().getStringExtra(EXTRA_TINGKAT_SKP);
        final String keterangan_skp = getIntent().getStringExtra(EXTRA_KETERANGAN_SKP);
        final String point_skp = getIntent().getStringExtra(EXTRA_POINT_SKP);

        uploadBuktiSkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        saveSkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSkp();
            }
        });

        spinerDetailSkp = (Spinner) findViewById(R.id.editDetailSkp);
        showDetailSkp();

        etTglAwal = (EditText) findViewById(R.id.editTanggalKepanitiaan);
        etTglAwal.setInputType(InputType.TYPE_NULL);
        etTglAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(UpdateSkpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etTglAwal.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        etTglAkhir = (EditText) findViewById(R.id.editTglAkhir);
        etTglAkhir.setInputType(InputType.TYPE_NULL);
        etTglAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(UpdateSkpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etTglAkhir.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        etNamaKegiatan.setText(nama_skp);
        etTglAwal.setText(tgl_awal_skp);
        etTglAkhir.setText(tgl_akhir_skp);
        etTempatKegiatan.setText(tempat_skp);

        Glide.with(this)
                .load(UtilsApi.BASE_URL_IMAGE+bukti_skp)
                .centerCrop()
                .placeholder(R.drawable.blank)
                .into(ivBuktiSkp);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Edit SKP");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void updateSkp(){

        nama_kegiatan = etNamaKegiatan.getText().toString();
        tgl_awal = etTglAwal.getText().toString();
        tgl_akhir = etTglAkhir.getText().toString();
        tempat_kegiatan = etTempatKegiatan.getText().toString();

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("id_skp1", RequestBody.create(MultipartBody.FORM, id_skp1));
        map.put("nama", RequestBody.create(MultipartBody.FORM, nama_kegiatan));
        map.put("tgl_awal", RequestBody.create(MultipartBody.FORM, tgl_awal));
        map.put("tgl_akhir", RequestBody.create(MultipartBody.FORM, tgl_akhir));
        map.put("tempat", RequestBody.create(MultipartBody.FORM, tempat_kegiatan));
        map.put("id_detail", RequestBody.create(MultipartBody.FORM, detailskp));
        map.put("id_user", RequestBody.create(MultipartBody.FORM, id_user));

        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .updateSkp(imageMB1,map)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getApplicationContext(), "SKP Update Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void showDetailSkp(){
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .showDetail()
                .enqueue(new Callback<List<DetailSkp>>() {
                    @Override
                    public void onResponse(Call<List<DetailSkp>> call, Response<List<DetailSkp>> response) {
                        detailSkpList.addAll(response.body());
                        spinnerCategory();
                    }

                    @Override
                    public void onFailure(Call<List<DetailSkp>> call, Throwable t) {

                    }
                });

    }

    private void spinnerCategory(){
        ArrayList<String> label = new ArrayList<>();

        int position = 0;
        for (int i =0;i<detailSkpList.size();i++){
            label.add(detailSkpList.get(i).getKategori()+" - "+ detailSkpList.get(i).getTingkat()+" - "+ detailSkpList.get(i).getKeterangan()+" - "+ detailSkpList.get(i).getPoint());
            if ((getIntent().getStringExtra(EXTRA_KATEGORI_SKP)+" - "+getIntent().getStringExtra(EXTRA_TINGKAT_SKP)+" - "+getIntent().getStringExtra(EXTRA_KETERANGAN_SKP)+" - "+getIntent().getStringExtra(EXTRA_POINT_SKP)).equals(
                    detailSkpList.get(i).getKategori()+" - "+ detailSkpList.get(i).getTingkat()+" - "+ detailSkpList.get(i).getKeterangan()+" - "+ detailSkpList.get(i).getPoint()
            )){
                position = i;
            }
        }
        detailskp = detailSkpList.get(position).getId_detail();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, label);

        spinerDetailSkp.setAdapter(adapter);
        spinerDetailSkp.setSelection(position, true);
//        View v = mCategory.getSelectedView();
//        ((TextView)v).setTextColor(getResources().getColor(R.color.md_black_1000));
        // mengeset listener untuk mengetahui saat item dipilih
        spinerDetailSkp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                detailskp = detailSkpList.get(i).getId_detail();
//                ((TextView) view).setTextColor(getResources().getColor(R.color.md_black_1000));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                ivBuktiSkp.setImageBitmap(bitmapFoto);

                File file = createTempFile(bitmapFoto);
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                imageMB1 = MultipartBody.Part.createFormData("bukti_skp", file.getName(), reqFile);
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
