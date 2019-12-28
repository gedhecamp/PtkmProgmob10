package com.example.ptkmprogmob.Model;

import com.google.gson.annotations.SerializedName;

public class Kepanitiaan {
    @SerializedName("id_kegiatan")
    private String id_kegiatan;
    @SerializedName("nama_kegiatan")
    private String nama_kegiatan;
    @SerializedName("tanggal_kegiatan")
    private String tanggal_kegiatan;
    @SerializedName("tempat_kegiatan")
    private String tempat_kegiatan;
    @SerializedName("desc_kegiatan")
    private String desc_kegiatan;
    @SerializedName("image_kegiatan")
    private String image_kegiatan;

    public Kepanitiaan() {
    }

    public Kepanitiaan(String id_kegiatan, String nama_kegiatan, String tanggal_kegiatan, String tempat_kegiatan, String desc_kegiatan, String image_kegiatan) {
        this.id_kegiatan = id_kegiatan;
        this.nama_kegiatan = nama_kegiatan;
        this.tanggal_kegiatan = tanggal_kegiatan;
        this.tempat_kegiatan = tempat_kegiatan;
        this.desc_kegiatan = desc_kegiatan;
        this.image_kegiatan = image_kegiatan;
    }

    public String getId_kegiatan() {
        return id_kegiatan;
    }

    public void setId_kegiatan(String id_kegiatan) {
        this.id_kegiatan = id_kegiatan;
    }

    public String getNama_kegiatan() {
        return nama_kegiatan;
    }

    public void setNama_kegiatan(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;
    }

    public String getTanggal_kegiatan() {
        return tanggal_kegiatan;
    }

    public void setTanggal_kegiatan(String tanggal_kegiatan) {
        this.tanggal_kegiatan = tanggal_kegiatan;
    }

    public String getTempat_kegiatan() {
        return tempat_kegiatan;
    }

    public void setTempat_kegiatan(String tempat_kegiatan) {
        this.tempat_kegiatan = tempat_kegiatan;
    }

    public String getDesc_kegiatan() {
        return desc_kegiatan;
    }

    public void setDesc_kegiatan(String desc_kegiatan) {
        this.desc_kegiatan = desc_kegiatan;
    }

    public String getImage_kegiatan() {
        return image_kegiatan;
    }

    public void setImage_kegiatan(String image_kegiatan) {
        this.image_kegiatan = image_kegiatan;
    }
}
