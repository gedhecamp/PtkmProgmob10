package com.example.ptkmprogmob.Model;

import com.google.gson.annotations.SerializedName;

public class Skp {
    @SerializedName("id_skp1")
    private String id_skp;
    @SerializedName("nama")
    private String namaSkp;
    @SerializedName("tgl_awal")
    private String tgl_awal;
    @SerializedName("tgl_akhir")
    private String tgl_akhir;
    @SerializedName("tempat")
    private String tempat_skp;
    @SerializedName("bukti_skp")
    private String bukti_skp;
    @SerializedName("isVerifed")
    private String isVerifed;
    @SerializedName("id_detail")
    private String id_detail;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("kategori")
    private String kategoriSkp;
    @SerializedName("tingkat")
    private String tingkatSkp;
    @SerializedName("keterangan")
    private String keteranganSkp;
    @SerializedName("point")
    private String pointSkp;

    public Skp() {
    }

    public Skp(String id_skp, String namaSkp, String tgl_awal, String tgl_akhir, String tempat_skp, String bukti_skp, String isVerifed, String id_detail, String id_user, String kategoriSkp, String tingkatSkp, String keteranganSkp, String pointSkp) {
        this.id_skp = id_skp;
        this.namaSkp = namaSkp;
        this.tgl_awal = tgl_awal;
        this.tgl_akhir = tgl_akhir;
        this.tempat_skp = tempat_skp;
        this.bukti_skp = bukti_skp;
        this.isVerifed = isVerifed;
        this.id_detail = id_detail;
        this.id_user = id_user;
        this.kategoriSkp = kategoriSkp;
        this.tingkatSkp = tingkatSkp;
        this.keteranganSkp = keteranganSkp;
        this.pointSkp = pointSkp;
    }

    public String getId_skp() {
        return id_skp;
    }

    public void setId_skp(String id_skp) {
        this.id_skp = id_skp;
    }

    public String getNamaSkp() {
        return namaSkp;
    }

    public void setNamaSkp(String namaSkp) {
        this.namaSkp = namaSkp;
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

    public String getTempat_skp() {
        return tempat_skp;
    }

    public void setTempat_skp(String tempat_skp) {
        this.tempat_skp = tempat_skp;
    }

    public String getBukti_skp() {
        return bukti_skp;
    }

    public void setBukti_skp(String bukti_skp) {
        this.bukti_skp = bukti_skp;
    }

    public String getIsVerifed() {
        return isVerifed;
    }

    public void setIsVerifed(String isVerifed) {
        this.isVerifed = isVerifed;
    }

    public String getId_detail() {
        return id_detail;
    }

    public void setId_detail(String id_detail) {
        this.id_detail = id_detail;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getKategoriSkp() {
        return kategoriSkp;
    }

    public void setKategoriSkp(String kategoriSkp) {
        this.kategoriSkp = kategoriSkp;
    }

    public String getTingkatSkp() {
        return tingkatSkp;
    }

    public void setTingkatSkp(String tingkatSkp) {
        this.tingkatSkp = tingkatSkp;
    }

    public String getKeteranganSkp() {
        return keteranganSkp;
    }

    public void setKeteranganSkp(String keteranganSkp) {
        this.keteranganSkp = keteranganSkp;
    }

    public String getPointSkp() {
        return pointSkp;
    }

    public void setPointSkp(String pointSkp) {
        this.pointSkp = pointSkp;
    }
}
