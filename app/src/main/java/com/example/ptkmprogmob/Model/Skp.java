package com.example.ptkmprogmob.Model;

import com.google.gson.annotations.SerializedName;

public class Skp {
    @SerializedName("id_skp")
    private String id_skp;
    @SerializedName("nama")
    private String nama_kegiatan;
    @SerializedName("tgl_awal")
    private String tgl_awal;
    @SerializedName("tgl_akhir")
    private String tgl_akhir;
    @SerializedName("tempat")
    private String tempat_kegiatan;
    @SerializedName("bukti_skp")
    private String bukti_skp;
    @SerializedName("id_detail")
    private String id_detail;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("point")
    private int point;


    public Skp() {
    }

    public Skp(String id_skp, String nama_kegiatan, String tgl_awal, String tgl_akhir, String tempat_kegiatan, String bukti_skp, String id_detail, String kategori, int point) {
        this.id_skp = id_skp;
        this.nama_kegiatan = nama_kegiatan;
        this.tgl_awal = tgl_awal;
        this.tgl_akhir = tgl_akhir;
        this.tempat_kegiatan = tempat_kegiatan;
        this.bukti_skp = bukti_skp;
        this.id_detail = id_detail;
        this.kategori = kategori;
        this.point = point;
    }

    public String getId_skp() {
        return id_skp;
    }

    public void setId_skp(String id_skp) {
        this.id_skp = id_skp;
    }

    public String getNama_kegiatan() {
        return nama_kegiatan;
    }

    public void setNama_kegiatan(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;
    }

    public String getTgl_awal() {
        return tgl_awal;
    }

    public void setTgl_awal(String tgl_awal) {
        this.tgl_awal = tgl_awal;
    }

    public String getTgl_akhir() {
        return tgl_akhir;
    }

    public void setTgl_akhir(String tgl_akhir) {
        this.tgl_akhir = tgl_akhir;
    }

    public String getTempat_kegiatan() {
        return tempat_kegiatan;
    }

    public void setTempat_kegiatan(String tempat_kegiatan) {
        this.tempat_kegiatan = tempat_kegiatan;
    }

    public String getBukti_skp() {
        return bukti_skp;
    }

    public void setBukti_skp(String bukti_skp) {
        this.bukti_skp = bukti_skp;
    }

    public String getId_detail() {
        return id_detail;
    }

    public void setId_detail(String id_detail) {
        this.id_detail = id_detail;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
