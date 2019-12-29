package com.example.ptkmprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ptkmprogmob.APIHelper.UtilsApi;

public class DetailKegiatanActivity extends AppCompatActivity {

    TextView namaK, tanggalK, tempatK, descK;
    ImageView imageK;

    public static final String EXTRA_ID_KEGIATAN = "id_kegiatan";
    public static final String EXTRA_NAMA_KEGIATAN = "nama_kegiatan";
    public static final String EXTRA_TANGGAL_KEGIATAN = "tanggal_kegiatan";
    public static final String EXTRA_TEMPAT_KEGIATAN = "tempat_kegiatan";
    public static final String EXTRA_DESC_KEGIATAN = "desc_kegiatan";
    public static final String EXTRA_IMAGE_KEGIATAN = "image_kegiatan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kegiatan);

        namaK = (TextView) findViewById(R.id.tvKegiatanNama);
        tanggalK = (TextView) findViewById(R.id.tvKegiatanTgl);
        tempatK = (TextView) findViewById(R.id.tvKegiatanTempat);
        descK = (TextView) findViewById(R.id.tvKegiatanDesc);
        imageK = (ImageView) findViewById(R.id.ivKegiatanImage);

        final String id_kegiatan = getIntent().getStringExtra(EXTRA_ID_KEGIATAN);
        final String nama_kegiatan = getIntent().getStringExtra(EXTRA_NAMA_KEGIATAN);
        final String tanggal_kegiatan = getIntent().getStringExtra(EXTRA_TANGGAL_KEGIATAN);
        final String tempat_kegiatan = getIntent().getStringExtra(EXTRA_TEMPAT_KEGIATAN);
        final String desc_kegiatan = getIntent().getStringExtra(EXTRA_DESC_KEGIATAN);
        final String image_kegiatan = getIntent().getStringExtra(EXTRA_IMAGE_KEGIATAN);

        namaK.setText(nama_kegiatan);
        tanggalK.setText(tanggal_kegiatan);
        tempatK.setText(tempat_kegiatan);
        descK.setText(desc_kegiatan);

        Glide.with(this)
                .load(UtilsApi.BASE_URL_IMAGE+image_kegiatan)
                .centerCrop()
                .placeholder(R.drawable.blank)
                .into(imageK);


        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle(nama_kegiatan);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
