package com.example.ptkmprogmob.Model;

import com.google.gson.annotations.SerializedName;

public class DetailSkp {
    @SerializedName("id_detail")
    private String id_detail;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("tingkat")
    private String tingkat;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("point")
    private int point;

    public DetailSkp() {
    }

    public DetailSkp(String id_detail, String kategori, String tingkat, String keterangan, int point) {
        this.id_detail = id_detail;
        this.kategori = kategori;
        this.tingkat = tingkat;
        this.keterangan = keterangan;
        this.point = point;
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

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
