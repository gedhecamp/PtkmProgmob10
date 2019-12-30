package com.example.ptkmprogmob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ptkmprogmob.Model.Kepanitiaan;
import com.example.ptkmprogmob.Model.Skp;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="skp_kegiatan.sql";
    private static final int DATABASE_VERSION=8;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_FAVORITE="CREATE TABLE "+ Skp.Entry.TABLE_NAME+" ( "+
                Skp.Entry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Skp.Entry.COLUMN_ID+" TEXT,"+
                Skp.Entry.COLUMN_NAMA_SKP+" TEXT,"+
                Skp.Entry.COLUMN_TGL_AWAL+" TEXT,"+
                Skp.Entry.COLUMN_TGL_AKHIR+" TEXT,"+
                Skp.Entry.COLUMN_TEMPAT_SKP+" TEXT,"+
                Skp.Entry.COLUMN_BUKTI_SKP+" TEXT,"+
                Skp.Entry.COLUMN_ISVERIVED+" TEXT,"+
                Skp.Entry.COLUMN_ID_DETAIL+" TEXT,"+
                Skp.Entry.COLUMN_ID_USER+" TEXT,"+
                Skp.Entry.COLUMN_KATEGORI_SKP+" TEXT,"+
                Skp.Entry.COLUMN_TINGKAT_SKP+" TEXT,"+
                Skp.Entry.COLUMN_KETERANGAN_SKP+" TEXT,"+
                Skp.Entry.COLUMN_POINT_SKP+" TEXT );";

        String CREATE_TABLE_PROFILE="CREATE TABLE "+ Kepanitiaan.Entry.TABLE_NAME+" ( "+
                Kepanitiaan.Entry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Kepanitiaan.Entry.COLUMN_ID+" INTEGER,"+
                Kepanitiaan.Entry.COLUMN_NAMA_KEGIATAN+" TEXT,"+
                Kepanitiaan.Entry.COLUMN_TANGGAL_KEGIATAN+" TEXT,"+
                Kepanitiaan.Entry.COLUMN_TEMPAT_KEGIATAN+" TEXT,"+
                Kepanitiaan.Entry.COLUMN_DESC_KEGIATAN+" TEXT,"+
                Kepanitiaan.Entry.COLUMN_IMAGE_KEGIATAN+" TEXT );";
        Log.d("debug", "Tabel Berhasil dibuat");
        sqLiteDatabase.execSQL(CREATE_TABLE_FAVORITE);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String DROP_TABLE_FAVORITE="DROP TABLE "+Skp.Entry.TABLE_NAME+";";
        String DROP_TABLE_PROFILE="DROP TABLE "+Kepanitiaan.Entry.TABLE_NAME+";";

        sqLiteDatabase.execSQL(DROP_TABLE_FAVORITE);
        sqLiteDatabase.execSQL(DROP_TABLE_PROFILE);
        Log.d("debug", "Tabel Berhasil di UodTW");
        onCreate(sqLiteDatabase);
    }

    //------------------------ Local Skp -----------------------------//
    public long insertSkp(String id_skp, String nama, String tgl_awal, String tgl_akhir, String tempat, String bukti_skp, String isVerived,
                               String id_detail, String id_user, String kategori_skp, String tingkat_skp, String keterangan_skp, String point_skp){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Skp.Entry.COLUMN_ID, id_skp);
        contentValues.put(Skp.Entry.COLUMN_NAMA_SKP, nama);
        contentValues.put(Skp.Entry.COLUMN_TGL_AWAL, tgl_awal);
        contentValues.put(Skp.Entry.COLUMN_TGL_AKHIR, tgl_akhir);
        contentValues.put(Skp.Entry.COLUMN_TEMPAT_SKP, tempat);
        contentValues.put(Skp.Entry.COLUMN_BUKTI_SKP, bukti_skp);
        contentValues.put(Skp.Entry.COLUMN_ISVERIVED, isVerived);
        contentValues.put(Skp.Entry.COLUMN_ID_DETAIL, id_detail);
        contentValues.put(Skp.Entry.COLUMN_ID_USER, id_user);
        contentValues.put(Skp.Entry.COLUMN_KATEGORI_SKP, kategori_skp);
        contentValues.put(Skp.Entry.COLUMN_TINGKAT_SKP, tingkat_skp);
        contentValues.put(Skp.Entry.COLUMN_KETERANGAN_SKP, keterangan_skp);
        contentValues.put(Skp.Entry.COLUMN_POINT_SKP, point_skp);

        long lastID=sqLiteDatabase.insert(Skp.Entry.TABLE_NAME, null, contentValues);

        return lastID;
    }

    public List<Skp> selectSkp(){
        List<Skp> skps = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase=getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Skp.Entry.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
            do {
                String id_skp = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_ID));
                String namaSkp = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_NAMA_SKP));
                String tgl_awal = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_TGL_AWAL));
                String tgl_akhir = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_TGL_AKHIR));
                String tempat_skp = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_TEMPAT_SKP));
                String bukti_skp = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_BUKTI_SKP));
                String isVerifed = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_ISVERIVED));
                String id_detail = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_ID_DETAIL));
                String id_user = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_ID_USER));
                String kategoriSkp = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_KATEGORI_SKP));
                String tingkatSkp = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_TINGKAT_SKP));
                String keteranganSkp = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_KETERANGAN_SKP));
                String pointSkp = cursor.getString(cursor.getColumnIndex(Skp.Entry.COLUMN_POINT_SKP));

                Skp tmp = new Skp(id_skp, namaSkp, tgl_awal, tgl_akhir, tempat_skp, bukti_skp, isVerifed, id_detail, id_user, kategoriSkp,
                        tingkatSkp, keteranganSkp, pointSkp);

                skps.add(tmp);
            }while(cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return skps;
    }

    public void deleteSkp(){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(Skp.Entry.TABLE_NAME, null, null);
    }

    //----------------------- End of Local Skp ----------------------//

    //------------------------ Local Profile User ------------------------------//
    public long insertKegiatan(String id_kegiatan, String nama_kegiatan, String tanggal_kegiatan, String tempat_kegiatan, String desc_kegiatan, String image_kegiatan){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Kepanitiaan.Entry.COLUMN_ID, id_kegiatan);
        contentValues.put(Kepanitiaan.Entry.COLUMN_NAMA_KEGIATAN, nama_kegiatan);
        contentValues.put(Kepanitiaan.Entry.COLUMN_TANGGAL_KEGIATAN, tanggal_kegiatan);
        contentValues.put(Kepanitiaan.Entry.COLUMN_TEMPAT_KEGIATAN, tempat_kegiatan);
        contentValues.put(Kepanitiaan.Entry.COLUMN_DESC_KEGIATAN, desc_kegiatan);
        contentValues.put(Kepanitiaan.Entry.COLUMN_IMAGE_KEGIATAN, image_kegiatan);

        long lastID=sqLiteDatabase.insert(Kepanitiaan.Entry.TABLE_NAME, null, contentValues);

        return lastID;
    }

    public List<Kepanitiaan> selectKegiatan(){
        List<Kepanitiaan> kepanitiaans = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase=getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Kepanitiaan.Entry.TABLE_NAME, null, null, null,null,
                null,null);

        if (cursor != null){
            cursor.moveToFirst();
            do {
                String id_kegiatan = cursor.getString(cursor.getColumnIndex(Kepanitiaan.Entry.COLUMN_ID));
                String nama_kegiatan = cursor.getString(cursor.getColumnIndex(Kepanitiaan.Entry.COLUMN_NAMA_KEGIATAN));
                String tanggal_kegiatan = cursor.getString(cursor.getColumnIndex(Kepanitiaan.Entry.COLUMN_TANGGAL_KEGIATAN));
                String tempat_kegiatan = cursor.getString(cursor.getColumnIndex(Kepanitiaan.Entry.COLUMN_TEMPAT_KEGIATAN));
                String desc_kegiatan = cursor.getString(cursor.getColumnIndex(Kepanitiaan.Entry.COLUMN_DESC_KEGIATAN));
                String image_kegiatan = cursor.getString(cursor.getColumnIndex(Kepanitiaan.Entry.COLUMN_IMAGE_KEGIATAN));

                Kepanitiaan tmp = new Kepanitiaan(id_kegiatan, nama_kegiatan, tanggal_kegiatan, tempat_kegiatan, desc_kegiatan, image_kegiatan);

                kepanitiaans.add(tmp);
            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return kepanitiaans;
    }

    public void deleteKegiatan(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(Kepanitiaan.Entry.TABLE_NAME, null, null);
    }
    //------------------------ End of Local Profile User ------------------------------//

}
