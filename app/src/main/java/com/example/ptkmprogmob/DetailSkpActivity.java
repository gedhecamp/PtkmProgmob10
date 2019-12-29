package com.example.ptkmprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ptkmprogmob.APIHelper.UtilsApi;

public class DetailSkpActivity extends AppCompatActivity {

    TextView namaS, tglAw_S, tglAk_S, tempatS, kategoriS, tingkatS, keteranganS, pointS;
    ImageView buktiS;

    public static final String EXTRA_ID_SKP = "id_skp1";
    public static final String EXTRA_NAMA_SKP = "nama";
    public static final String EXTRA_TANGGAL_AWAL = "tgl_awal";
    public static final String EXTRA_TANGGAL_AKHIR = "tgl_akhir";
    public static final String EXTRA_TEMPAT_SKP = "tempat";
    public static final String EXTRA_BUKTI_SKP = "bukti_skp";
    public static final String EXTRA_KATEGORI_SKP = "kategori";
    public static final String EXTRA_TINGKAT_SKP = "tingkat";
    public static final String EXTRA_KETERANGAN_SKP = "keterangan";
    public static final String EXTRA_POINT_SKP = "point";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_skp);

        namaS = findViewById(R.id.tvNamaSkp);
        tglAw_S = findViewById(R.id.tvTglAwal);
        tglAk_S = findViewById(R.id.tvTglAkhir);
        tempatS = findViewById(R.id.tvTempatSkp);
        buktiS = findViewById(R.id.ivBuktiSkp);
        kategoriS = findViewById(R.id.tvKategori);
        tingkatS = findViewById(R.id.tvTingkat);
        keteranganS = findViewById(R.id.tvKeterangan);
        pointS = findViewById(R.id.tvPoint);

        final String nama_skp = getIntent().getStringExtra(EXTRA_NAMA_SKP);
        final String tgl_awal_skp = getIntent().getStringExtra(EXTRA_TANGGAL_AWAL);
        final String tgl_akhir_skp = getIntent().getStringExtra(EXTRA_TANGGAL_AKHIR);
        final String tempat_skp = getIntent().getStringExtra(EXTRA_TEMPAT_SKP);
        final String bukti_skp = getIntent().getStringExtra(EXTRA_BUKTI_SKP);
        final String kategori_skp = getIntent().getStringExtra(EXTRA_KATEGORI_SKP);
        final String tingkat_skp = getIntent().getStringExtra(EXTRA_TINGKAT_SKP);
        final String keterangan_skp = getIntent().getStringExtra(EXTRA_KETERANGAN_SKP);
        final String point_skp = getIntent().getStringExtra(EXTRA_POINT_SKP);

        namaS.setText(nama_skp);
        tglAw_S.setText(tgl_awal_skp);
        tglAk_S.setText(tgl_akhir_skp);
        tempatS.setText(tempat_skp);
        kategoriS.setText(kategori_skp);
        tingkatS.setText(tingkat_skp);
        keteranganS.setText(keterangan_skp);
        pointS.setText(point_skp);

        Glide.with(this)
                .load(UtilsApi.BASE_URL_IMAGE+bukti_skp)
                .centerCrop()
                .placeholder(R.drawable.blank)
                .into(buktiS);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle(nama_skp);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
